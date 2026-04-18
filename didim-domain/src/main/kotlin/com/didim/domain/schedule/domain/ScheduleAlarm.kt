package com.didim.domain.schedule.domain

import com.didim.domain.schedule.domain.vo.AlarmTime
import java.time.LocalDateTime

data class ScheduleAlarm(
    val alarmTime: AlarmTime,
) {
    companion object {
        fun of(alarmTime: LocalDateTime) = ScheduleAlarm(
            alarmTime = AlarmTime.of(alarmTime),
        )
    }

    val time: LocalDateTime
        get() = alarmTime.alarmTime
}
