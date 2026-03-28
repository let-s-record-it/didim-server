package com.didim.domain.calendar.domain

data class Calendar(
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
        ) = Calendar(
            id = id,
            title = CalendarTitle(title),
            categoryId = categoryId,
            memberKey = memberKey,
        )
    }

    val titleValue: String
        get() = title.title
}