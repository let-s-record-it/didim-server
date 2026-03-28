package com.didim.domain.calendar.domain

import com.didim.domain.calendar.domain.vo.CalendarCategoryName
import com.didim.domain.calendar.domain.vo.CalendarColorHex

data class CalendarCategory(
    val colorHex: CalendarColorHex,
    val name: CalendarCategoryName,
    val isDefault: Boolean,
    val memberId: Long,
    val id: Long? = null,
) {
    constructor(colorHex: String, name: String, isDefault: Boolean, memberId: Long)
            : this(CalendarColorHex(colorHex), CalendarCategoryName(name), isDefault, memberId)

    val colorHexValue: String
        get() = colorHex.colorHex

    val nameValue: String
        get() = name.name
}