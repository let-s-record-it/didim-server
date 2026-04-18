package com.didim.api.task.dto.request

import com.didim.api.support.validation.ValidDayOfMonth
import com.didim.api.support.validation.ValidMonth
import com.didim.api.support.validation.ValidWeekdayBit
import com.didim.common.enums.RepetitionType
import com.didim.common.enums.WeekNumber
import com.didim.common.enums.Weekday
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.time.LocalDate

data class TaskRepetitionUpdateRequest(
    val repetitionType: RepetitionType,
    @field:Min(1)
    @field:Max(999)
    val repetitionPeriod: Int,
    val repetitionStartDate: LocalDate,
    val repetitionEndDate: LocalDate,
    @field:ValidMonth
    val monthOfYear: Int,
    @field:ValidDayOfMonth
    val dayOfMonth: Int,
    val weekNumber: WeekNumber,
    val weekday: Weekday,
    @field:ValidWeekdayBit
    val weekdayBit: Int,
)
