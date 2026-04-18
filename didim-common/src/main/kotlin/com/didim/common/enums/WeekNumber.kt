package com.didim.common.enums

import java.time.LocalDate
import java.time.Period

enum class WeekNumber(val value: Int) {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    ;

    fun contains(date: LocalDate) : Boolean {
        val firstDayOfMonth = date.withDayOfMonth(1)

        return date.isAfter(firstDayOfMonth.plus(Period.ofDays(7 * (value - 1) - 1)))
    }
}