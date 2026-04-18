package com.didim.alarm

import com.didim.domain.alarm.implement.PushNotificationSender
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PushAlarmJob : Job {

    @Autowired
    private lateinit var pushNotificationSender: PushNotificationSender

    override fun execute(context: JobExecutionContext) {
        val jobDataMap = context.jobDetail.jobDataMap

        val title = jobDataMap.getString("title")
        val body = jobDataMap.getString("body")

        @Suppress("UNCHECKED_CAST")
        val fcmTokens = jobDataMap["fcmTokens"] as List<String>

        @Suppress("UNCHECKED_CAST")
        val payload = jobDataMap["payload"] as? Map<String, String> ?: emptyMap()

        pushNotificationSender.send(
            tokens = fcmTokens,
            title = title,
            body = body,
            data = payload,
        )
    }
}
