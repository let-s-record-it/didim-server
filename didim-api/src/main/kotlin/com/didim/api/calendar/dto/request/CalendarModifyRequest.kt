package com.didim.api.calendar.dto.request

import com.didim.domain.calendar.domain.EditCalendar
import jakarta.validation.constraints.Size

data class CalendarModifyRequest(
    @field:Size(min = 1, max = 30)
    val title: String,
    val calendarCategoryId: Long,
) {
    fun toEditCalendar(calendarId: Long, memberKey: String) = EditCalendar.of(
        id = calendarId,
        title = title,
        categoryId = calendarCategoryId,
        memberKey = memberKey,
    )
}
