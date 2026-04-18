package com.didim.api.goal.dto.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class WeeklyGoalUpdateRequest(
    @field:NotBlank
    @field:Size(max = 30)
    val title: String,
    @field:Size(max = 500)
    val description: String,
    @field:Min(1)
    @field:Max(100)
    val week: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val categoryId: Long,
    val calendarId: Long,
    val relatedMonthlyGoalId: Long,
) {
}