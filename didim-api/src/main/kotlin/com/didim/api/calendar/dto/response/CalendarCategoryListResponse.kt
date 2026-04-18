package com.didim.api.calendar.dto.response

import com.didim.domain.calendar.domain.CalendarCategory

data class CalendarCategoryListResponse(
    val id: Long,
    val colorHex: String,
    val name: String,
    val isDefault: Boolean,
) {
    companion object {
        fun from(calendarCategory: CalendarCategory) = CalendarCategoryListResponse(
            id = calendarCategory.id,
            colorHex = calendarCategory.colorHexValue,
            name = calendarCategory.nameValue,
            isDefault = calendarCategory.isDefault,
        )
    }
}