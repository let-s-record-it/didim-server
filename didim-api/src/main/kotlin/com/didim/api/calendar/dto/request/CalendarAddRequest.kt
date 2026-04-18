package com.didim.api.calendar.dto.request

import com.didim.domain.calendar.domain.NewCalendar
import jakarta.validation.constraints.Size

data class CalendarAddRequest(
    @param:Size(min = 1, max = 30)
    val title: String,
    val calendarCategoryId: Long,
) {
    fun toNewCalendar(memberKey: String) = NewCalendar.of(
        title = title,
        categoryId = calendarCategoryId,
        memberKey = memberKey,
    )
}
