package com.didim.domain.alarm.domain

import java.time.LocalDateTime

data class ScheduleAddMessage(
    val id: Long,
    val title: String,
    val isAllDay: Boolean,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val colorHex: String,
    val calendarId: Long,
)
