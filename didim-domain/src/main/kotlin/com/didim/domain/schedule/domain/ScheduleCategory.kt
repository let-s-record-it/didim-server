package com.didim.domain.schedule.domain

import com.didim.domain.schedule.domain.vo.ScheduleCategoryName
import com.didim.domain.schedule.domain.vo.ScheduleColorHex

data class ScheduleCategory(
    val colorHex: ScheduleColorHex,
    val name: ScheduleCategoryName,
    val isDefault: Boolean,
    val calendarId: Long,
    val id: Long,
) {
    companion object {
        fun of(
            id: Long,
            colorHex: String,
            name: String,
            isDefault: Boolean,
            calendarId: Long,
        ) = ScheduleCategory(
            id = id,
            colorHex = ScheduleColorHex(colorHex),
            name = ScheduleCategoryName(name),
            isDefault = isDefault,
            calendarId = calendarId,
        )
    }

    val colorHexValue
        get() = colorHex.colorHex

    val nameValue
        get() = name.name
}