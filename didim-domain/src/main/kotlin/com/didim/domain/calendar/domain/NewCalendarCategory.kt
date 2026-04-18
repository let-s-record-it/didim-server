package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarCategoryName
import com.didim.domain.calendar.domain.vo.CalendarColorHex

data class NewCalendarCategory(
    val colorHex: CalendarColorHex,
    val name: CalendarCategoryName,
    val memberKey: String,
) {
    companion object {
        fun of(
            colorHex: String,
            name: String,
            memberKey: String,
        ) = NewCalendarCategory(
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
