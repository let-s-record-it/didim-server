package com.didim.domain.schedule.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import java.time.DayOfWeek

@JvmInline
value class WeekdayBit(
    val weekdayBit: Int,
) {
    companion object {
        private const val MIN_WEEKDAY_BIT = 0
        private const val MAX_WEEKDAY_BIT = 127

        private val SUNDAY_FIRST_ORDER = listOf(
            DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
        )
    }

    init {
        if (weekdayBit !in MIN_WEEKDAY_BIT..MAX_WEEKDAY_BIT) {
            throw AppException(ErrorType.WEEKDAY_BIT_OUT_OF_RANGE)
        }
    }

    fun isValid(dayOfWeek: DayOfWeek) = (weekdayBit and (1 shl (dayOfWeek.value - 1))) > 0

    val activeDays: List<DayOfWeek>
        get() = SUNDAY_FIRST_ORDER.filter { isValid(it) }
}