package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarTitle

data class NewCalendar(
    val title: CalendarTitle,
    val categoryId: Long,
    val memberKey: String,
) {
    companion object {
        fun of(
            title: String,
            categoryId: Long,
            memberKey: String,
        ) = NewCalendar(
            title = CalendarTitle(title),
            categoryId = categoryId,
            memberKey = memberKey,
        )
    }

    val titleValue: String
        get() = title.title
}