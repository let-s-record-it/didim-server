package com.didim.domain.schedulecategory.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType


data class ScheduleColorHex(
    val colorHex: String
) {
    companion object {
        private const val COLOR_HEX_REGEX = "[0-9a-fA-F]{8}|[0-9a-fA-F]{6}|[0-9a-fA-F]{3}"
    }

    init {
        validate(colorHex)
    }

    private fun validate(colorHex: String) {
        if (!colorHex.matches(COLOR_HEX_REGEX.toRegex())) {
            throw AppException(ErrorType.INVALID_SCHEDULE_COLOR_HEX)
        }
    }
}
