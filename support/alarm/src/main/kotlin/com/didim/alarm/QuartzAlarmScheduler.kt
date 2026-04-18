package com.didim.alarm

import com.didim.domain.alarm.domain.PushAlarm
import com.didim.domain.alarm.domain.vo.JobGroupIdentity
import com.didim.domain.alarm.implement.AlarmScheduler
import com.didim.domain.member.domain.MemberDevice
import com.didim.domain.member.implement.MemberDeviceManager
import org.quartz.JobBuilder
import org.quartz.Scheduler
import org.quartz.TriggerBuilder
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.ZoneId
import java.util.*

@Component
@Transactional
class QuartzAlarmScheduler(
    private val memberDeviceManager: MemberDeviceManager,
    private val scheduler: Scheduler,
) : AlarmScheduler {

    override fun schedule(pushAlarm: PushAlarm) {
        val fcmTokens = memberDeviceManager.findByMemberKey(pushAlarm.memberKey)
            .map(MemberDevice::fcmToken)

        pushAlarm.alarmTimes.forEach {
            val name = "${pushAlarm.alarmType.name}-${pushAlarm.memberKey}-$it"
            val group = pushAlarm.jobGroupIdentity.value
            val job = JobBuilder.newJob(PushAlarmJob::class.java)
                .withIdentity(name, group)
                .build()

            job.jobDataMap["title"] = pushAlarm.title
            job.jobDataMap["body"] = pushAlarm.body
            job.jobDataMap["memberKey"] = pushAlarm.memberKey
            job.jobDataMap["fcmTokens"] = fcmTokens
            job.jobDataMap["payload"] = pushAlarm.payload

            val date = Date.from(it.atZone(ZoneId.systemDefault()).toInstant())
            val trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .startAt(date)
                .build()

            scheduler.scheduleJob(job, trigger)
            scheduler.start()
        }
    }

    override fun unschedule(jobGroupIdentity: JobGroupIdentity) {
        scheduler.getJobKeys(GroupMatcher.groupEquals(jobGroupIdentity.value)).forEach { jobKey ->
            scheduler.getTriggersOfJob(jobKey).forEach { trigger ->
                scheduler.unscheduleJob(trigger.key)
            }

            scheduler.deleteJob(jobKey)
        }
    }
}
