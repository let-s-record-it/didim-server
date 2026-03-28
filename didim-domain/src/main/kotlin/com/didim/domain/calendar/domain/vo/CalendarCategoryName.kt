package com.didim.domain.calendar.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

@JvmInline
value class CalendarCategoryName(
    val name: String,
) {
    companion object {
        private const val MAX_CATEGORY_NAME_LENGTH = 10
    }

    init {
        validateCategoryNameIsNotBlank()
        validateCategoryNameLength()
    }

    private fun validateCategoryNameIsNotBlank() {
        if (name.isBlank()) {
            throw AppException(ErrorType.BLANK_CALENDAR_CATEGORY_NAME)
        }

    }

    private fun validateCategoryNameLength() {
        if (name.length > MAX_CATEGORY_NAME_LENGTH) {
            throw AppException(ErrorType.INVALID_CALENDAR_CATEGORY_NAME_LENGTH)
        }
    }
}
