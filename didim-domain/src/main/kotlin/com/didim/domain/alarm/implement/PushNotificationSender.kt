package com.didim.domain.alarm.implement

interface PushNotificationSender {
    fun send(
        tokens: List<String>,
        title: String,
        body: String,
        data: Map<String, String> = emptyMap(),
    )
}
