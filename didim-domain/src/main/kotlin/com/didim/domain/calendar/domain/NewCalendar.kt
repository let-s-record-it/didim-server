package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarTitle

data class NewCalendar(
    val title: CalendarTitle,
    val categoryId: Long,
    val memberKey: String,
) {
    val titleValue: String
        get() = title.title
}