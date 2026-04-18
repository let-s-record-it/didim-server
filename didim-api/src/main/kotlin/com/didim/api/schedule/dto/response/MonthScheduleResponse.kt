package com.didim.api.schedule.dto.response

import com.didim.domain.schedule.domain.Schedule
import java.time.LocalDateTime

data class MonthScheduleResponse(
    val id: Long,
    val title: String,
    val isAllDay: Boolean,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val colorHex: String,
) {
    companion object {
        fun from(schedule: Schedule) = MonthScheduleResponse(
            id = schedule.id,
            title = schedule.titleValue,
            isAllDay = schedule.isAllDay,
            startDateTime = schedule.startDateTime,
            endDateTime = schedule.endDateTime,
            colorHex = schedule.colorHex,
        )
    }
}
