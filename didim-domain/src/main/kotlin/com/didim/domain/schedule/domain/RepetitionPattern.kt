package com.didim.domain.schedule.domain

import com.didim.common.enums.RepetitionType
import com.didim.common.enums.WeekNumber
import com.didim.common.enums.Weekday
import com.didim.domain.schedule.domain.vo.DayOfMonth
import com.didim.domain.schedule.domain.vo.MonthOfYear
import com.didim.domain.schedule.domain.vo.RepetitionDuration
import com.didim.domain.schedule.domain.vo.WeekdayBit
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Period
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

class RepetitionPattern(
    val repetitionType: RepetitionType,
    val repetitionPeriod: Int,
    val repetitionDuration: RepetitionDuration,
    val scheduleGroupId: Long? = null,
    val monthOfYear: MonthOfYear? = null,
    val dayOfMonth: DayOfMonth? = null,
    val weekNumber: WeekNumber? = null,
    val weekday: Weekday? = null,
    val weekdayBit: WeekdayBit? = null,
) {
    companion object {
        fun of(
            repetitionType: RepetitionType,
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            scheduleGroupId: Long,
            monthOfYear: Int? = null,
            dayOfMonth: Int? = null,
            weekNumber: WeekNumber? = null,
            weekday: Weekday? = null,
            weekdayBit: Int? = null,
        ) = when (repetitionType) {
            RepetitionType.DAILY -> daily(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                scheduleGroupId = scheduleGroupId,
            )

            RepetitionType.WEEKLY -> weekly(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                weekdayBit = weekdayBit!!,
                scheduleGroupId = scheduleGroupId,
            )

            RepetitionType.MONTHLY_WITH_DATE -> monthlyWithDate(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                dayOfMonth = dayOfMonth!!,
                scheduleGroupId = scheduleGroupId,
            )

            RepetitionType.MONTHLY_WITH_WEEKDAY -> monthlyWithWeekday(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                weekNumber = weekNumber!!,
                weekday = weekday!!,
                scheduleGroupId = scheduleGroupId,
            )

            RepetitionType.MONTHLY_WITH_LAST_DAY -> monthlyWithLastDay(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                scheduleGroupId = scheduleGroupId,
            )

            RepetitionType.YEARLY_WITH_DATE -> yearlyWithDate(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                monthOfYear = monthOfYear!!,
                dayOfMonth = dayOfMonth!!,
                scheduleGroupId = scheduleGroupId,
            )

            RepetitionType.YEARLY_WITH_WEEKDAY -> yearlyWithWeekday(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                monthOfYear = monthOfYear!!,
                weekNumber = weekNumber!!,
                weekday = weekday!!,
                scheduleGroupId = scheduleGroupId,
            )

            RepetitionType.YEARLY_WITH_LAST_DAY -> yearlyWithLastDay(
                repetitionPeriod = repetitionPeriod,
                repetitionStartDate = repetitionStartDate,
                repetitionEndDate = repetitionEndDate,
                monthOfYear = monthOfYear!!,
                scheduleGroupId = scheduleGroupId,
            )
        }

        fun daily(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.DAILY,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            scheduleGroupId = scheduleGroupId,
        )

        fun weekly(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            weekdayBit: Int,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.WEEKLY,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            weekdayBit = WeekdayBit(weekdayBit),
            scheduleGroupId = scheduleGroupId,
        )

        fun monthlyWithDate(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            dayOfMonth: Int,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.MONTHLY_WITH_DATE,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            dayOfMonth = DayOfMonth(dayOfMonth),
            scheduleGroupId = scheduleGroupId,
        )

        fun monthlyWithWeekday(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            weekNumber: WeekNumber,
            weekday: Weekday,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.MONTHLY_WITH_WEEKDAY,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            weekNumber = weekNumber,
            weekday = weekday,
            scheduleGroupId = scheduleGroupId,
        )

        fun monthlyWithLastDay(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.MONTHLY_WITH_LAST_DAY,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            scheduleGroupId = scheduleGroupId,
        )

        fun yearlyWithDate(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            monthOfYear: Int,
            dayOfMonth: Int,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.YEARLY_WITH_DATE,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            monthOfYear = MonthOfYear(monthOfYear),
            dayOfMonth = DayOfMonth(dayOfMonth),
            scheduleGroupId = scheduleGroupId,
        )

        fun yearlyWithWeekday(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            monthOfYear: Int,
            weekNumber: WeekNumber,
            weekday: Weekday,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.YEARLY_WITH_WEEKDAY,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            monthOfYear = MonthOfYear(monthOfYear),
            weekNumber = weekNumber,
            weekday = weekday,
            scheduleGroupId = scheduleGroupId,
        )

        fun yearlyWithLastDay(
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            monthOfYear: Int,
            scheduleGroupId: Long,
        ) = RepetitionPattern(
            repetitionType = RepetitionType.YEARLY_WITH_LAST_DAY,
            repetitionPeriod = repetitionPeriod,
            repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
            monthOfYear = MonthOfYear(monthOfYear),
            scheduleGroupId = scheduleGroupId,
        )
    }

    val startDate get() = repetitionDuration.repetitionStartDate
    val endDate get() = repetitionDuration.repetitionStartDate
    val monthOfYearValue get() = monthOfYear?.monthOfYear
    val dayOfMonthValue get() = dayOfMonth?.dayOfMonth
    val weekdayBitValue get() = weekdayBit?.weekdayBit

    private val endDateInclusive get() = repetitionDuration.repetitionEndDate.plusDays(1L)

    fun repeating(toSkipTemporal: Int = 0): Sequence<Period> = when (repetitionType) {
        RepetitionType.DAILY -> dailyRepeating()
        RepetitionType.WEEKLY -> weeklyRepeating()
        RepetitionType.MONTHLY_WITH_DATE -> monthlyWithDateRepeating()
        RepetitionType.MONTHLY_WITH_WEEKDAY -> monthlyWithWeekdayRepeating()
        RepetitionType.MONTHLY_WITH_LAST_DAY -> monthlyWithLastDayRepeating()
        RepetitionType.YEARLY_WITH_DATE -> yearlyWithDateRepeating()
        RepetitionType.YEARLY_WITH_WEEKDAY -> yearlyWithWeekdayRepeating()
        RepetitionType.YEARLY_WITH_LAST_DAY -> yearlyWithLastDayRepeating()
    }.drop(toSkipTemporal)

    private fun dailyRepeating(): Sequence<Period> =
        generateSequence(0) { it + repetitionPeriod }
            .takeWhile { startDate.plusDays(it.toLong()).isBeforeEndInclusive() }
            .map { Period.ofDays(it) }

    private fun weeklyRepeating(): Sequence<Period> {
        val activeDays = weekdayBit?.activeDays ?: return emptySequence()
        val firstWeekStart = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

        return generateSequence(firstWeekStart) { it.plusWeeks(repetitionPeriod.toLong()) }
            .flatMap { weekStart -> activeDays.map { weekStart.with(TemporalAdjusters.nextOrSame(it)) } }
            .dropWhile { it < startDate }
            .takeWhile { it.isBeforeEndInclusive() }
            .map { daysFromStart(it) }
    }

    private fun monthlyWithDateRepeating(): Sequence<Period> =
        generateSequence(startDate) { it.plusMonths(repetitionPeriod.toLong()) }
            .takeWhile { it.isBeforeEndInclusive() }
            .map { daysFromStart(it) }

    private fun monthlyWithWeekdayRepeating(): Sequence<Period> =
        generateSequence(startDate) { it.plusMonths(repetitionPeriod.toLong()) }
            .map { findDayOfWeekInMonth(it) }
            .takeWhile { it.isBeforeEndInclusive() }
            .map { daysFromStart(it) }

    private fun monthlyWithLastDayRepeating(): Sequence<Period> =
        generateSequence(startDate) { it.plusMonths(repetitionPeriod.toLong()) }
            .takeWhile { it.isBeforeEndInclusive() }
            .map { daysFromStart(it.with(TemporalAdjusters.lastDayOfMonth())) }

    private fun yearlyWithDateRepeating(): Sequence<Period> =
        generateSequence(startDate) { it.plusYears(repetitionPeriod.toLong()) }
            .takeWhile { it.isBeforeEndInclusive() }
            .filter { dayOfMonth?.equalsDayOfMonth(it.dayOfMonth) == true }
            .map { daysFromStart(it) }

    private fun yearlyWithWeekdayRepeating(): Sequence<Period> =
        generateSequence(startDate) { it.plusYears(repetitionPeriod.toLong()) }
            .map { findDayOfWeekInMonth(it) }
            .takeWhile { it.isBeforeEndInclusive() }
            .map { daysFromStart(it) }

    private fun yearlyWithLastDayRepeating(): Sequence<Period> =
        generateSequence(startDate) { it.plusYears(repetitionPeriod.toLong()) }
            .takeWhile { it.isBeforeEndInclusive() }
            .map { daysFromStart(it.with(TemporalAdjusters.lastDayOfMonth())) }

    private fun LocalDateTime.isBeforeEndInclusive(): Boolean = isBefore(endDateInclusive)

    private fun daysFromStart(date: LocalDateTime): Period =
        Period.ofDays(ChronoUnit.DAYS.between(startDate, date).toInt())

    private fun findDayOfWeekInMonth(date: LocalDateTime): LocalDateTime =
        date.with(TemporalAdjusters.dayOfWeekInMonth(weekNumber!!.value, weekday!!.toDayOfWeek()))
}
