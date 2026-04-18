package com.didim.api.task.dto.request

import java.time.LocalDate

data class TaskAddRequest(
    val title: String,
    val description: String,
    val date: LocalDate,
    val isRepeated: Boolean,
    val categoryId: Long,
    val repetition: TaskRepetitionUpdateRequest,
    val taskGroup: TaskGroupUpdateRequest,
)
