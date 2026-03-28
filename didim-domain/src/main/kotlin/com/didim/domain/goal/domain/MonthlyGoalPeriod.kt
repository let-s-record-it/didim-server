package com.didim.domain.goal.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import java.time.LocalDate
import java.time.YearMonth

data class MonthlyGoalPeriod(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    companion object {
        private const val START_DAY_OF_MONTH = 1
    }
    
    init {
        validateHasSameYearAndMonth()
        validateStartDateHasFirstDayOfMonth()
        validateEndDateHashLastDayOfMonth()
    }

    private fun validateHasSameYearAndMonth() {
        if (!hasSameYearAndMonth()) {
            throw AppException(ErrorType.DIFFERENT_YEAR_MONTH)
        }
    }

    private fun validateStartDateHasFirstDayOfMonth() {
        if (startDate.dayOfMonth != START_DAY_OF_MONTH) {
            throw AppException(ErrorType.INVALID_START_DAY_OF_MONTH)
        }
    }

    private fun validateEndDateHashLastDayOfMonth() {
        val lastDayOfMonth = YearMonth.from(endDate).lengthOfMonth()
        if (endDate.dayOfMonth != lastDayOfMonth) {
            throw AppException(ErrorType.INVALID_END_DAY_OF_MONTH)
        }
    }

    private fun hasSameYearAndMonth() =
        startDate.year == endDate.year && startDate.month == endDate.month;
}