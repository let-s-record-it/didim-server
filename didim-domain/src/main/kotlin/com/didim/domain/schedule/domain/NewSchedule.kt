package com.didim.domain.schedule.domain

import com.didim.common.enums.RepetitionType
import com.didim.common.enums.WeekNumber
import com.didim.common.enums.Weekday
import com.didim.domain.schedule.domain.vo.AlarmTime
import com.didim.domain.schedule.domain.vo.DayOfMonth
import com.didim.domain.schedule.domain.vo.Location
import com.didim.domain.schedule.domain.vo.MonthOfYear
import com.didim.domain.schedule.domain.vo.RepetitionDuration
import com.didim.domain.schedule.domain.vo.ScheduleDescription
import com.didim.domain.schedule.domain.vo.ScheduleDuration
import com.didim.domain.schedule.domain.vo.ScheduleTitle
import com.didim.domain.schedule.domain.vo.WeekdayBit
import java.time.LocalDateTime

data class NewSchedule(
    val title: ScheduleTitle,
    val description: ScheduleDescription,
    val scheduleDuration: ScheduleDuration,
    val place: String,
    val setLocation: Boolean,
    val location: Location,
    val setAlarm: Boolean,
    val alarmTimes: List<ScheduleAlarm>,
    val categoryId: Long,
    val calendarId: Long,
    val memberKey: String,
    val isRepeated: Boolean,
    val repetitionPattern: RepetitionPattern,
) {
    companion object {
        fun of(
            title: String,
            description: String,
            isAllDay: Boolean,
            startDateTime: LocalDateTime,
            endDateTime: LocalDateTime,
            place: String,
            setLocation: Boolean,
            latitude: Double,
            longitude: Double,
            setAlarm: Boolean,
            alarmTimes: List<LocalDateTime>,
            categoryId: Long,
            calendarId: Long,
            memberKey: String,
            isRepeated: Boolean,
            repetitionType: RepetitionType,
            repetitionPeriod: Int,
            repetitionStartDate: LocalDateTime,
            repetitionEndDate: LocalDateTime,
            monthOfYear: Int? = null,
            dayOfMonth: Int? = null,
            weekNumber: WeekNumber? = null,
            weekday: Weekday? = null,
            weekdayBit: Int? = null,
        ) = NewSchedule(
            title = ScheduleTitle(title),
            description = ScheduleDescription(description),
            scheduleDuration = ScheduleDuration.of(isAllDay, startDateTime, endDateTime),
            place = place,
            setLocation = setLocation,
            location = Location(latitude, longitude),
            setAlarm = setAlarm,
            alarmTimes = alarmTimes.map { ScheduleAlarm.of(it) },
            categoryId = categoryId,
            calendarId = calendarId,
            memberKey = memberKey,
            isRepeated = isRepeated,
            repetitionPattern = RepetitionPattern(
                repetitionType = repetitionType,
                repetitionPeriod = repetitionPeriod,
                repetitionDuration = RepetitionDuration(repetitionStartDate, repetitionEndDate),
                monthOfYear = monthOfYear?.let(::MonthOfYear),
                dayOfMonth = dayOfMonth?.let(::DayOfMonth),
                weekNumber = weekNumber,
                weekday = weekday,
                weekdayBit = weekdayBit?.let(::WeekdayBit),
                scheduleGroupId = 0,
            )
        )
    }

    val titleValue: String
        get() = title.title

    val descriptionValue: String
        get() = description.description

    val isAllDay: Boolean
        get() = scheduleDuration.isAllDay

    val startDateTime: LocalDateTime
        get() = scheduleDuration.startDateTime

    val endDateTime: LocalDateTime
        get() = scheduleDuration.endDateTime

    val latitude: Double
        get() = location.latitude

    val longitude: Double
        get() = location.longitude

    fun toRepeatedSchedules(toSkipTemporal: Int = 0): List<NewSchedule> = repetitionPattern.repeating(toSkipTemporal)
        .map {
            NewSchedule(
                title = title,
                description = description,
                scheduleDuration = ScheduleDuration.of(
                    isAllDay = scheduleDuration.isAllDay,
                    startDateTime = scheduleDuration.startDateTime.plus(it),
                    endDateTime = scheduleDuration.endDateTime.plus(it)
                ),
                place = place,
                setLocation = setLocation,
                location = location,
                setAlarm = setAlarm,
                alarmTimes = alarmTimes,
                categoryId = categoryId,
                calendarId = calendarId,
                memberKey = memberKey,
                isRepeated = isRepeated,
                repetitionPattern = repetitionPattern,
            )
        }
        .toList()
}