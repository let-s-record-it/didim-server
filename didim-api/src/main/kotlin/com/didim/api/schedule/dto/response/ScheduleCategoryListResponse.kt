package com.didim.api.schedule.dto.response

import com.didim.domain.schedule.domain.ScheduleCategory

data class ScheduleCategoryListResponse(
    val id: Long,
    val colorHex: String,
    val name: String,
    val isDefault: Boolean,
) {
    companion object {
        fun from(scheduleCategory: ScheduleCategory) = ScheduleCategoryListResponse(
            id = scheduleCategory.id,
            colorHex = scheduleCategory.colorHexValue,
            name = scheduleCategory.nameValue,
            isDefault = scheduleCategory.isDefault,
        )
    }
}
