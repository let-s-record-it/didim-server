package com.didim.domain.calendar.domain

data class NewCalendar(
    val title: CalendarTitle,
    val categoryId: Long,
    val memberKey: String,
) {
    val titleValue: String
        get() = title.title
}