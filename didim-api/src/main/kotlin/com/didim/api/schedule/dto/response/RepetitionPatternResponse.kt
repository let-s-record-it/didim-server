package com.didim.api.schedule.dto.response

import com.didim.common.enums.RepetitionType
import com.didim.common.enums.WeekNumber
import com.didim.common.enums.Weekday
import com.didim.domain.schedule.domain.RepetitionPattern
import java.time.LocalDateTime

data class RepetitionPatternResponse(
    val repetitionType: RepetitionType,
    val repetitionPeriod: Int,
    val repetitionStartDate: LocalDateTime,
    val repetitionEndDate: LocalDateTime,
    val monthOfYear: Int?,
    val dayOfMonth: Int?,
    val weekNumber: WeekNumber?,
    val weekday: Weekday?,
    val repeatedWeekday: RepeatedWeekday?,
) {
    companion object {
        fun from(repetitionPattern: RepetitionPattern?) = if (repetitionPattern != null) RepetitionPatternResponse(
            repetitionType = repetitionPattern.repetitionType,
            repetitionPeriod = repetitionPattern.repetitionPeriod,
            repetitionStartDate = repetitionPattern.startDate,
            repetitionEndDate = repetitionPattern.endDate,
            monthOfYear = repetitionPattern.monthOfYearValue,
            dayOfMonth = repetitionPattern.dayOfMonthValue,
            weekNumber = repetitionPattern.weekNumber,
            weekday = repetitionPattern.weekday,
            repeatedWeekday = RepeatedWeekday.from(repetitionPattern.weekdayBit),
        ) else null
    }
}
