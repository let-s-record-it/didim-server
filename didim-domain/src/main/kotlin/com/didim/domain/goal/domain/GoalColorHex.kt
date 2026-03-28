package com.didim.domain.goal.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class GoalColorHex(
    val colorHex: String,
) {
    companion object {
        private const val COLOR_HEX_REGEX = "[0-9a-fA-F]{8}|[0-9a-fA-F]{6}|[0-9a-fA-F]{3}"
    }

    init {
        validateColorHexFormat()
    }

    private fun validateColorHexFormat() {
        if (colorHex.matches(COLOR_HEX_REGEX.toRegex())) {
            throw AppException(ErrorType.INVALID_GOAL_COLOR_HEX)
        }
    }
}
