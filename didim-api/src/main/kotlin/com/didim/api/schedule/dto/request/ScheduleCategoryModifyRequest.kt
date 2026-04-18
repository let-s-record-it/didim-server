package com.didim.api.schedule.dto.request

import com.didim.api.support.validation.ValidColorHex
import com.didim.domain.schedule.domain.EditScheduleCategory
import org.hibernate.validator.constraints.Length

data class ScheduleCategoryModifyRequest(
    @field:ValidColorHex
    val colorHex: String,
    @field:Length(min = 1, max = 10)
    val name: String,
) {
    fun toEditScheduleCategory(id: Long) = EditScheduleCategory.of(
        id = id,
        colorHex = colorHex,
        name = name,
    )
}