package com.didim.domain.schedule.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class MonthOfYear(
    val monthOfYear: Int,
) {
    companion object {
        private const val MIN_MONTH_OF_YEAR = 1
        private const val MAX_MONTH_OF_YEAR = 12
    }

    init {
        validateMonthOfYearRange()
    }

    private fun validateMonthOfYearRange() {
        if (monthOfYear !in MIN_MONTH_OF_YEAR..MAX_MONTH_OF_YEAR) {
            throw AppException(ErrorType.INVALID_MONTH_OF_YEAR)
        }
    }
}
