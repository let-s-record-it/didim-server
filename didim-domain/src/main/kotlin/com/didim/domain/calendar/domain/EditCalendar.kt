package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarTitle

data class EditCalendar(
    val id: Long,
    val title: CalendarTitle,
    val categoryId: Long,
    val memberKey: String,
) {
    companion object {
        fun of(
            id: Long,
            title: String,
            categoryId: Long,
            memberKey: String,
        ) = EditCalendar(
            id = id,
            title = CalendarTitle(title),
            categoryId = categoryId,
            memberKey = memberKey,
        )
    }
    val titleValue: String
        get() = title.title
}