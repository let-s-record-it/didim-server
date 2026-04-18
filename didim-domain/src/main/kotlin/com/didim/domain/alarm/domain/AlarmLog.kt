package com.didim.domain.alarm.domain

data class AlarmLog(
    val alarmType: AlarmType,
    val content: String,
    val senderKey: String,
    val receiverKey: String,
    val id: Long? = null,
) {
}