package com.didim.api.goal.dto.response

import java.time.LocalDate

data class WeeklyGoalDetailsResponse(
    val id: Long,
    val title: String,
    val description: String,
    val week: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val categoryId: Long,
    val colorHex: String,
    val calendarId: Long,
    val relatedMonthlyGoal: RelatedMonthlyGoalResponse,
)
