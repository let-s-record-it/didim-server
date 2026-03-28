package com.didim.api.calendar.dto.response

import com.didim.domain.calendar.domain.Calendar

data class CalendarResponse(
    val id: Long,
    val title: String,
    val colorHex: String,
    val categoryId: Long,
) {
    companion object {
        fun from(calendar: Calendar) = CalendarResponse(
            id = calendar.id,
            title = calendar.titleValue,
            categoryId = calendar.categoryId,
        )
    }
}
