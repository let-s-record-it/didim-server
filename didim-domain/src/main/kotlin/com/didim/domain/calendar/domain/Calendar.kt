package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarTitle

data class Calendar(
    val id: Long,
    val title: CalendarTitle,
    val categoryId: Long,
    val categoryColorHex: String,
    val memberKey: String,
) {
    companion object {
        fun of(
            id: Long,
            title: String,
            categoryId: Long,
            categoryColorHex: String,
            memberKey: String,
        ) = Calendar(
            id = id,
            title = CalendarTitle(title),
            categoryId = categoryId,
            categoryColorHex = categoryColorHex,
            memberKey = memberKey,
        )
    }

    val titleValue: String
        get() = title.title

    fun isOwner(memberKey: String) = this.memberKey == memberKey
}