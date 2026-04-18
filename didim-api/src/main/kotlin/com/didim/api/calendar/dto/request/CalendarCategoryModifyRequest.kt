package com.didim.api.calendar.dto.request

import com.didim.api.support.validation.ValidColorHex
import com.didim.domain.calendar.domain.EditCalendarCategory
import jakarta.validation.constraints.Size

data class CalendarCategoryModifyRequest(
    @field:ValidColorHex
    val colorHex: String,
    @field:Size(min = 1, max = 10)
    val name: String,
) {
    fun toEditCalendarCategory(id: Long, memberKey: String) = EditCalendarCategory.of(
        id = id,
        colorHex = colorHex,
        name = name,
        memberKey = memberKey,
    )
}
