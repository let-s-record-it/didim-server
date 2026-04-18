package com.didim.common.enums

import java.time.DayOfWeek
import java.time.LocalDate

enum class Weekday(val value: Int) {
    MON(1),
    TUE(2),
    WED(3),
    THU(4),
    FRI(5),
    SAT(6),
    SUN(7),
    ;

    fun toDayOfWeek(): DayOfWeek = DayOfWeek.of(value)

    fun hasSameWeekday(date: LocalDate): Boolean = value == date.dayOfWeek.value
}