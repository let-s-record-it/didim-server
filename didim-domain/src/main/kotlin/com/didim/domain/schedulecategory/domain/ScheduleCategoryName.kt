package com.didim.domain.schedulecategory.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class ScheduleCategoryName(
    val name: String
) {
    companion object {
        private const val MAX_CATEGORY_NAME_LENGTH = 10
    }

    init {
        validate(name)
    }

    private fun validate(name: String) {
        if (name.isBlank()) {
            throw AppException(ErrorType.BLANK_SCHEDULE_CATEGORY_NAME)
        }

        if (name.length > MAX_CATEGORY_NAME_LENGTH) {
            throw AppException(ErrorType.INVALID_SCHEDULE_CATEGORY_NAME_LENGTH)
        }
    }
}