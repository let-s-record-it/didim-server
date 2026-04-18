package com.didim.domain.schedule.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

@JvmInline
value class ScheduleColorHex(
    val colorHex: String
) {
    companion object {
        private const val COLOR_HEX_REGEX = "[0-9a-fA-F]{8}|[0-9a-fA-F]{6}|[0-9a-fA-F]{3}"
    }

    init {
        validateColorHexFormat()
    }

    private fun validateColorHexFormat() {
        if (!colorHex.matches(COLOR_HEX_REGEX.toRegex())) {
            throw AppException(ErrorType.INVALID_CALENDAR_COLOR_HEX)
        }
    }
}