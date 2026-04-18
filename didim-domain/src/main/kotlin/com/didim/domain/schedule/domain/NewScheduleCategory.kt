package com.didim.domain.schedule.domain

import com.didim.domain.schedule.domain.vo.ScheduleCategoryName
import com.didim.domain.schedule.domain.vo.ScheduleColorHex

data class NewScheduleCategory(
    val colorHex: ScheduleColorHex,
    val name: ScheduleCategoryName,
    val isDefault: Boolean,
    val calendarId: Long,
) {
    companion object {
        fun of(
            colorHex: String,
            name: String,
            isDefault: Boolean,
            calendarId: Long,
        ) = NewScheduleCategory(
            colorHex = ScheduleColorHex(colorHex),
            name = ScheduleCategoryName(name),
            isDefault = isDefault,
            calendarId = calendarId,
        )
    }

    val colorHexValue: String
        get() = colorHex.colorHex

    val nameValue: String
        get() = name.name
}
