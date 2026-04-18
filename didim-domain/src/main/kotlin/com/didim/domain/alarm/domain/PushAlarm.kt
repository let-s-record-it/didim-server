package com.didim.domain.alarm.domain

import com.didim.domain.alarm.domain.vo.JobGroupIdentity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ConsistentCopyVisibility
data class PushAlarm private constructor(
    val jobGroupIdentity: JobGroupIdentity,
    val alarmType: AlarmType,
    val alarmTimes: List<LocalDateTime>,
    val title: String,
    val body: String,
    val payload: Map<String, Any>,
    val memberKey: String,
) {
    companion object {
        fun scheduleAlarmOf(
            scheduleId: Long,
            alarmTimes: List<LocalDateTime>,
            payload: Map<String, Any>,
            memberKey: String,
            startDateTime: LocalDateTime,
            scheduleTitle: String,
        ) = PushAlarm(
            jobGroupIdentity = JobGroupIdentity.scheduleAlarm(memberKey, scheduleId),
            alarmType = AlarmType.SCHEDULE_ALARM,
            alarmTimes = alarmTimes,
            title = AlarmType.SCHEDULE_ALARM.title,
            body = AlarmType.SCHEDULE_ALARM.body.format(
                startDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 (E)")),
                scheduleTitle,
            ),
            payload = payload,
            memberKey = memberKey,
        )
    }
}