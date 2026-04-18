package com.didim.domain.schedule.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

@JvmInline
value class DayOfMonth(
    val dayOfMonth: Int,
) {
    companion object {
        fun createMonthly(dayOfMonth: Int): DayOfMonth {
            validateDateRange(dayOfMonth)
            return DayOfMonth(dayOfMonth)
        }

        fun createYearly(monthOfYear: Int, dayOfMonth: Int): DayOfMonth {
            validateDateRangeInMonth(monthOfYear, dayOfMonth)
            return DayOfMonth(dayOfMonth)
        }

        private fun validateDateRange(date: Int) {
            if (date !in 1..31) {
                throw AppException(ErrorType.INVALID_DAY_OF_MONTH)
            }
        }

        private fun validateDateRangeInMonth(monthOfYear: Int, dayOfMonth: Int) {
            validateDateRange(dayOfMonth)

            if (monthOfYear == 2 && dayOfMonth > 29) {
                throw AppException(ErrorType.INVALID_DAY_OF_MONTH)
            }

            if ((monthOfYear == 4 || monthOfYear == 6 || monthOfYear == 9 || monthOfYear == 11)
                && dayOfMonth > 30
            ) {
                throw AppException(ErrorType.INVALID_DAY_OF_MONTH)
            }
        }
    }

    fun equalsDayOfMonth(dayOfMonth: Int) = this.dayOfMonth == dayOfMonth
}