package com.didim.domain.alarm.domain

data class AlarmMessage<T>(
    val id: Long,
    val type: AlarmType,
    val content: T,
) {
}