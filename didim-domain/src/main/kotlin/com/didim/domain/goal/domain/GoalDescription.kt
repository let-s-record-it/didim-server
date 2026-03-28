package com.didim.domain.goal.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class GoalDescription(
    val description: String,
) {
    companion object {
        private const val MAX_DESCRIPTION_LENGTH = 500
    }

    init {
        validateDescriptionLength()
    }

    private fun validateDescriptionLength() {
        if (description.length > MAX_DESCRIPTION_LENGTH) {
            throw AppException(ErrorType.INVALID_GOAL_DESCRIPTION_LENGTH)
        }
    }
}
