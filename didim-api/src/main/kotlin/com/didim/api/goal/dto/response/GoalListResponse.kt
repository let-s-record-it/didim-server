package com.didim.api.goal.dto.response

data class GoalListResponse(
    val monthlyGoals: List<GoalResponse>,
    val weeklyGoals: List<GoalResponse>,
)
