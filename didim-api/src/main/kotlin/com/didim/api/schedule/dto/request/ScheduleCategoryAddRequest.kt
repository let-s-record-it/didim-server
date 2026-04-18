package com.didim.api.schedule.dto.request

import com.didim.api.support.validation.ValidColorHex
import com.didim.domain.schedule.domain.NewScheduleCategory
import org.hibernate.validator.constraints.Length

data class ScheduleCategoryAddRequest(
    @field:ValidColorHex
    val colorHex: String,
    @field:Length(min = 1, max = 10)
    val name: String,
) {
    fun toNewScheduleCategory(calendarId: Long) = NewScheduleCategory.of(
        colorHex = colorHex,
        name = name,
        isDefault = false,
        calendarId = calendarId,
    )
}
