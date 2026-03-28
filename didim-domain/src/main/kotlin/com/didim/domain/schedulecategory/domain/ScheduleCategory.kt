package com.didim.domain.schedulecategory.domain

data class ScheduleCategory(
    val colorHex: ScheduleColorHex,
    val name: ScheduleCategoryName,
    val isDefault: Boolean,
    val calendarId: Long,
    val id: Long? = null,
) {
    constructor(colorHex: String, name: String, isDefault: Boolean, calendarId: Long)
            : this(ScheduleColorHex(colorHex), ScheduleCategoryName(name), isDefault, calendarId)

    val colorHexValue
        get() = colorHex.colorHex

    val nameValue
        get() = name.name
}