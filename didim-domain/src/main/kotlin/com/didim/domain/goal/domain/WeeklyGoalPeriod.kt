package com.didim.domain.goal.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class WeeklyGoalPeriod(
    val week: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    companion object {
        private const val SIX_DAYS = 6L
    }

    init {
        validateStartDateIsMondayOrSunday()
        validateDifferenceBetweenStartDateAndEndDate()
    }

    private fun validateStartDateIsMondayOrSunday() {
        if (!startDateIsMondayOrSunday()) {
            throw AppException(ErrorType.INVALID_START_DAY_OF_WEEK)
        }
    }

    private fun validateDifferenceBetweenStartDateAndEndDate() {
        if (ChronoUnit.DAYS.between(startDate, endDate) != SIX_DAYS) {
            throw AppException(ErrorType.INVALID_DIFFERENCE_OF_DATE)
        }
    }

    private fun startDateIsMondayOrSunday() =
        startDate.dayOfWeek == DayOfWeek.SUNDAY || startDate.dayOfWeek == DayOfWeek.MONDAY
}