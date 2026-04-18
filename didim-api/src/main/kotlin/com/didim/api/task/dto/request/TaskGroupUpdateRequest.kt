package com.didim.api.task.dto.request

data class TaskGroupUpdateRequest(
    val relatedMonthlyGoalId: Long,
    val relatedWeeklyGoalId: Long,
)
