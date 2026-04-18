package com.didim.domain.schedule.domain.vo

import java.time.LocalDateTime

@JvmInline
value class AlarmTime private constructor(
    val alarmTime: LocalDateTime,
) {
    companion object {
        fun of(alarmTime: LocalDateTime) = AlarmTime(alarmTime.withSecond(0))
    }
}