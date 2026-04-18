package com.didim.api.goal.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class MonthlyGoalUpdateRequest(
    @field:NotBlank
    @field:Size(max = 30)
    val title: String,
    @field:Size(max = 500)
    val description: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val categoryId: Long,
    val calendarId: Long,
) {
}