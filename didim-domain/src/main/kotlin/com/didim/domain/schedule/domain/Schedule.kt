package com.didim.domain.schedule.domain

import com.didim.common.enums.RepetitionType
import com.didim.common.enums.WeekNumber
import com.didim.common.enums.Weekday
import com.didim.domain.schedule.domain.vo.DayOfMonth
import com.didim.domain.schedule.domain.vo.Location
import com.didim.domain.schedule.domain.vo.MonthOfYear
import com.didim.domain.schedule.domain.vo.RepetitionDuration
import com.didim.domain.schedule.domain.vo.ScheduleDescription
import com.didim.domain.schedule.domain.vo.ScheduleDuration
import com.didim.domain.schedule.domain.vo.ScheduleTitle
import com.didim.domain.schedule.domain.vo.WeekdayBit
import java.time.LocalDateTime

data class Schedule(
    val id: Long,
    val title: ScheduleTitle,
    val description: ScheduleDescription,
    val scheduleDuration: ScheduleDuration,
    val place: String,
    val setLocation: Boolean,
    val location: Location,
    val setAlarm: Boolean,
    val scheduleAlarms: List<ScheduleAlarm>,
    val scheduleCategory: ScheduleCategory,
    val calendarId: Long,
    val calendarTitle: String,
    val scheduleGroupId: Long,
    val memberKey: String,
    val isRepeated: Boolean,
    val repetitionPattern: RepetitionPattern?,
) {
    companion object {
        fun of(
            id: Long,
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
            categoryColorHex: String,
            categoryName: String,
            categoryIsDefault: Boolean,
            calendarId: Long,
            calendarTitle: String,
            scheduleGroupId: Long,
            memberKey: String,
            isRepeated: Boolean,
            repetitionType: RepetitionType?,
            repetitionPeriod: Int?,
            repetitionStartDate: LocalDateTime?,
            repetitionEndDate: LocalDateTime?,
            monthOfYear: Int? = null,
            dayOfMonth: Int? = null,
            weekNumber: WeekNumber? = null,
            weekday: Weekday? = null,
            weekdayBit: Int? = null,
        ) = Schedule(
            id = id,
            title = ScheduleTitle(title),
            description = ScheduleDescription(description),
            scheduleDuration = ScheduleDuration.of(isAllDay, startDateTime, endDateTime),
            place = place,
            setLocation = setLocation,
            location = Location(latitude, longitude),
            setAlarm = setAlarm,
            scheduleAlarms = alarmTimes.map { ScheduleAlarm.of(it) },
            scheduleCategory = ScheduleCategory.of(
                id = categoryId,
                colorHex = categoryColorHex,
                name = categoryName,
                isDefault = categoryIsDefault,
                calendarId = calendarId,
            ),
            calendarId = calendarId,
            calendarTitle = calendarTitle,
            memberKey = memberKey,
            isRepeated = isRepeated,
            scheduleGroupId = scheduleGroupId,
            repetitionPattern = if (isRepeated) RepetitionPattern(
                repetitionType = repetitionType!!,
                repetitionPeriod = repetitionPeriod!!,
                repetitionDuration = RepetitionDuration(repetitionStartDate!!, repetitionEndDate!!),
                monthOfYear = monthOfYear?.let(::MonthOfYear),
                dayOfMonth = dayOfMonth?.let(::DayOfMonth),
                weekNumber = weekNumber,
                weekday = weekday,
                weekdayBit = weekdayBit?.let(::WeekdayBit),
                scheduleGroupId = scheduleGroupId,
            ) else null
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

    val categoryId: Long
        get() = scheduleCategory.id

    val colorHex: String
        get() = scheduleCategory.colorHexValue

    val latitude: Double
        get() = location.latitude

    val longitude: Double
        get() = location.longitude

    val alarmTimes: List<LocalDateTime>
        get() = scheduleAlarms.map(ScheduleAlarm::time)
}