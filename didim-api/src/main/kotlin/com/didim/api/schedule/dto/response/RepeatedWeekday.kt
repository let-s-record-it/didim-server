package com.didim.api.schedule.dto.response

import com.didim.domain.schedule.domain.vo.WeekdayBit
import java.time.DayOfWeek

data class RepeatedWeekday(
    val monday: Boolean,
    val tuesday: Boolean,
    val wednesday: Boolean,
    val thursday: Boolean,
    val friday: Boolean,
    val saturday: Boolean,
    val sunday: Boolean,
) {
    companion object {
        fun from(weekdayBit: WeekdayBit?) = if (weekdayBit != null) RepeatedWeekday(
            monday = weekdayBit.isValid(DayOfWeek.MONDAY),
            tuesday = weekdayBit.isValid(DayOfWeek.TUESDAY),
            wednesday = weekdayBit.isValid(DayOfWeek.WEDNESDAY),
            thursday = weekdayBit.isValid(DayOfWeek.THURSDAY),
            friday = weekdayBit.isValid(DayOfWeek.FRIDAY),
            saturday = weekdayBit.isValid(DayOfWeek.SATURDAY),
            sunday = weekdayBit.isValid(DayOfWeek.SUNDAY),
        ) else null
    }
}