package com.didim.domain.alarm.domain

import java.time.LocalDateTime

data class ScheduleDeleteMessage(
    val scheduleId: Long,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val calendarId: Long,
)
