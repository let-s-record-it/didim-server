package com.didim.domain.alarm.domain

data class ScheduleAlarmMessage(
    val scheduleId: Long,
    val title: String,
    val body: String,
)