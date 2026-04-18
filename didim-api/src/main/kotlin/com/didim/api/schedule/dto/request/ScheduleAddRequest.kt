package com.didim.api.schedule.dto.request

import com.didim.api.support.validation.ValidLatitude
import com.didim.api.support.validation.ValidLongitude
import com.didim.domain.schedule.domain.NewSchedule
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime

data class ScheduleAddRequest(
    @field:Size(min = 1, max = 30)
    val title: String,
    @field:Size(max = 500)
    val description: String,
    val isAllDay: Boolean,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val isRepeated: Boolean,
    @Validated
    val repetition: RepetitionUpdateRequest,
    val place: String,
    val setLocation: Boolean,
    @field:ValidLatitude
    val latitude: Double,
    @field:ValidLongitude
    val longitude: Double,
    val setAlarm: Boolean,
    val categoryId: Long,
    val alarmTimes: List<LocalDateTime>,
) {
    fun toNewSchedule(calendarId: Long, memberKey: String) = NewSchedule.of(
        title = title,
        description = description,
        isAllDay = isAllDay,
        startDateTime = startDateTime,
        endDateTime = endDateTime,
        place = place,
        setLocation = setLocation,
        latitude = latitude,
        longitude = longitude,
        setAlarm = setAlarm,
        alarmTimes = alarmTimes,
        categoryId = categoryId,
        calendarId = calendarId,
        memberKey = memberKey,
        isRepeated = isRepeated,
        repetitionType = repetition.repetitionType,
        repetitionPeriod = repetition.repetitionPeriod,
        repetitionStartDate = repetition.repetitionStartDate,
        repetitionEndDate = repetition.repetitionEndDate,
        monthOfYear = repetition.monthOfYear,
        dayOfMonth = repetition.dayOfMonth,
        weekNumber = repetition.weekNumber,
        weekday = repetition.weekday,
        weekdayBit = repetition.weekdayBit,
    )
}
