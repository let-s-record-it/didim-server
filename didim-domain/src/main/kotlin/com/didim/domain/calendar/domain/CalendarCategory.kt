package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarCategoryName
import com.didim.domain.calendar.domain.vo.CalendarColorHex

data class CalendarCategory(
    val colorHex: CalendarColorHex,
    val name: CalendarCategoryName,
    val isDefault: Boolean,
    val memberKey: String,
    val id: Long,
) {
    companion object {
        fun of(id: Long, colorHex: String, name: String, isDefault: Boolean, memberKey: String) = CalendarCategory(
            id = id,
            colorHex = CalendarColorHex(colorHex),
            name = CalendarCategoryName(name),
            isDefault = isDefault,
            memberKey = memberKey,
        )
    }

    val colorHexValue: String
        get() = colorHex.colorHex

    val nameValue: String
        get() = name.name

    fun isOwner(memberKey: String) = this.memberKey == memberKey
}