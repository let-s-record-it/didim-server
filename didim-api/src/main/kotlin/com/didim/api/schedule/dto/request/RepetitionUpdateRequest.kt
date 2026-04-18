package com.didim.api.schedule.dto.request

import com.didim.api.support.validation.ValidDayOfMonth
import com.didim.api.support.validation.ValidMonth
import com.didim.api.support.validation.ValidWeekdayBit
import com.didim.common.enums.RepetitionType
import com.didim.common.enums.WeekNumber
import com.didim.common.enums.Weekday
import org.hibernate.validator.constraints.Range
import java.time.LocalDateTime

data class RepetitionUpdateRequest(
    val repetitionType: RepetitionType,
    @field:Range(min = 1, max = 999)
    val repetitionPeriod: Int,
    val repetitionStartDate: LocalDateTime,
    val repetitionEndDate: LocalDateTime,
    @ValidMonth
    val monthOfYear: Int?,
    @ValidDayOfMonth
    val dayOfMonth: Int?,
    val weekNumber: WeekNumber?,
    val weekday: Weekday?,
    @ValidWeekdayBit
    val weekdayBit: Int?,
)
