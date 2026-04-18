package com.didim.api.goal.dto.response

data class WeeklyGoalResponse(
    val id: Long,
    val title: String,
    val week: Int,
    val colorHex: String,
    val achieved: Boolean,
    val relatedMonthlyGoal: RelatedMonthlyGoalResponse,
)
