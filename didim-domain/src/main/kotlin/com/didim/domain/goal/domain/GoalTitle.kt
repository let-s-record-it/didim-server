package com.didim.domain.goal.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class GoalTitle(
    val title: String
) {
    companion object {
        private const val MAX_TITLE_LENGTH = 30
    }

    init {
        validateTitleIsNotBlank()
        validateTitleLength()
    }

    private fun validateTitleIsNotBlank() {
        if (title.isBlank()) {
            throw AppException(ErrorType.BLANK_GOAL_TITLE)
        }
    }

    private fun validateTitleLength() {
        if (title.length > MAX_TITLE_LENGTH) {
            throw AppException(ErrorType.INVALID_GOAL_TITLE_LENGTH)
        }
    }
}
