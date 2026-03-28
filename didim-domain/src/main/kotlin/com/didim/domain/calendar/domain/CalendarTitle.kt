package com.didim.domain.calendar.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType


@JvmInline
value class CalendarTitle(
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
            throw AppException(ErrorType.BLANK_CALENDAR_TITLE)
        }

    }

    private fun validateTitleLength() {
        if (title.length > MAX_TITLE_LENGTH) {
            throw AppException(ErrorType.INVALID_CALENDAR_TITLE_LENGTH)
        }
    }
}