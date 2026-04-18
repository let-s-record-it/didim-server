package com.didim.api.task.dto.request

import java.time.LocalDate

data class TaskUpdateRequest(
    val newTitle: String,
    val newDescription: String,
    val date: LocalDate,
    val newCategoryId: Long,
    val newCalendarId: Long,
    val isRepeated: Boolean,
    val newRepetition: TaskRepetitionUpdateRequest,
    val newTaskGroup: TaskGroupUpdateRequest,
)
