package com.didim.domain.schedule.domain

import com.didim.domain.schedule.domain.vo.ScheduleCategoryName
import com.didim.domain.schedule.domain.vo.ScheduleColorHex

data class EditScheduleCategory(
    val id: Long,
    val colorHex: ScheduleColorHex,
    val name: ScheduleCategoryName,
) {
    companion object {
        fun of(
            id: Long,
            colorHex: String,
            name: String,
        ) = EditScheduleCategory(
            id = id,
            colorHex = ScheduleColorHex(colorHex),
            name = ScheduleCategoryName(name),
        )
    }

    val colorHexValue: String
        get() = colorHex.colorHex

    val nameValue: String
        get() = name.name
}