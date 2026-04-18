package com.didim.api.schedule.dto.response

import com.didim.domain.schedule.domain.Schedule
import java.time.LocalDateTime

data class DayScheduleResponse(
    val id: Long,
    val title: String,
    val description: String,
    val isAllDay: Boolean,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val categoryId: Long,
    val colorHex: String,
    val place: String,
    val setLocation: Boolean,
    val latitude: Double,
    val longitude: Double,
    val setAlarm: Boolean,
    val alarmTimes: List<LocalDateTime>,
    val isRepeated: Boolean,
    val calendarId: Long,
    val calendarTitle: String,
    val repetitionPattern: RepetitionPatternResponse?,
) {
    companion object {
        fun from(schedule: Schedule) = DayScheduleResponse(
            id = schedule.id,
            title = schedule.titleValue,
            description = schedule.descriptionValue,
            isAllDay = schedule.isAllDay,
            startDateTime = schedule.startDateTime,
            endDateTime = schedule.endDateTime,
            categoryId = schedule.categoryId,
            colorHex = schedule.colorHex,
            place = schedule.place,
            setLocation = schedule.setLocation,
            latitude = schedule.latitude,
            longitude = schedule.longitude,
            setAlarm = schedule.setAlarm,
            alarmTimes = schedule.alarmTimes,
            isRepeated = schedule.isRepeated,
            calendarId = schedule.calendarId,
            calendarTitle = schedule.calendarTitle,
            repetitionPattern = RepetitionPatternResponse.from(schedule.repetitionPattern),
        )
    }
}
