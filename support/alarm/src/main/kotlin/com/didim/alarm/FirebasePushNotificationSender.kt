package com.didim.alarm

import com.didim.domain.alarm.implement.PushNotificationSender
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Component

@Component
class FirebasePushNotificationSender : PushNotificationSender {

    override fun send(
        tokens: List<String>,
        title: String,
        body: String,
        data: Map<String, String>,
    ) {
        if (tokens.isEmpty()) return

        val notification = Notification.builder()
            .setTitle(title)
            .setBody(body)
            .build()

        val message = MulticastMessage.builder()
            .setNotification(notification)
            .putAllData(data)
            .addAllTokens(tokens)
            .build()

        FirebaseMessaging.getInstance().sendEachForMulticast(message)
    }
}
