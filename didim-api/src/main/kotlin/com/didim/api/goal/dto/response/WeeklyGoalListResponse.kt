package com.didim.api.goal.dto.response

data class WeeklyGoalListResponse(
    val week: Int,
    val weeklyGoals: List<WeeklyGoalResponse>,
)
