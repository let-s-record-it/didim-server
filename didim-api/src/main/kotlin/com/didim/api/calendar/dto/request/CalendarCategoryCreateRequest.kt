package com.didim.api.calendar.dto.request

import com.didim.api.support.validation.ValidColorHex
import com.didim.domain.calendar.domain.NewCalendarCategory
import jakarta.validation.constraints.Size

data class CalendarCategoryCreateRequest(
    @field:ValidColorHex
    val colorHex: String,
    @field:Size(min = 1, max = 10)
    val name: String,
) {
    fun toNewCalendarCategory(memberKey: String) = NewCalendarCategory.of(
        colorHex = colorHex,
        name = name,
        memberKey = memberKey,
    )
}
