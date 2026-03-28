package com.didim.api.calendar.dto.request

import com.didim.domain.calendar.domain.Calendar
import org.hibernate.validator.constraints.Length

data class CalendarAddRequest(
    @param:Length(min = 1, max = 30)
    val title: String,
    val calendarCategoryId: Long,
) {
    fun toCalendar(): Calendar {

    }
}
