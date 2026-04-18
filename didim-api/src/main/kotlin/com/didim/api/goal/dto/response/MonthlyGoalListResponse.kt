package com.didim.api.goal.dto.response

data class MonthlyGoalListResponse(
    val id: Long,
    val title: String,
    val colorHex: String,
    val achieved: Boolean,
) {
}