package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarCategoryName
import com.didim.domain.calendar.domain.vo.CalendarColorHex

class EditCalendarCategory(
    val id: Long,
    val colorHex: CalendarColorHex,
    val name: CalendarCategoryName,
    val memberKey: String,
) {
    companion object {
        fun of(
            id: Long,
            colorHex: String,
            name: String,
            memberKey: String,
        ) = EditCalendarCategory(
            id = id,
            colorHex = CalendarColorHex(colorHex),
            name = CalendarCategoryName(name),
            memberKey = memberKey,
        )
    }
    val colorHexValue: String
        get() = colorHex.colorHex

    val nameValue: String
        get() = name.name
}