package com.didim.dbmain.schedule.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.dbmain.calendar.entity.CalendarEntity
import com.didim.domain.schedule.domain.EditSchedule
import com.didim.domain.schedule.domain.NewSchedule
import com.didim.domain.schedule.domain.RepetitionPattern
import com.didim.domain.schedule.domain.Schedule
import com.didim.domain.schedule.domain.ScheduleAlarm
import com.didim.domain.schedule.domain.vo.Location
import com.didim.domain.schedule.domain.vo.ScheduleDescription
import com.didim.domain.schedule.domain.vo.ScheduleDuration
import com.didim.domain.schedule.domain.vo.ScheduleTitle
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "schedule")
@Entity
internal class ScheduleEntity(
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var description: String,
    @Column(nullable = false)
    var isAllDay: Boolean,
    @Column(nullable = false)
    var startDateTime: LocalDateTime,
    @Column(nullable = false)
    var endDateTime: LocalDateTime,
    @Column(nullable = false)
    var place: String,
    @Column(nullable = false)
    var setLocation: Boolean,
    @Column
    var latitude: Double,
    @Column
    var longitude: Double,
    @Column(nullable = false)
    var setAlarm: Boolean,
    @Column(nullable = false)
    var scheduleCategoryId: Long,
    @Column(nullable = false)
    var calendarId: Long,
    @Column(nullable = false)
    var scheduleGroupId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    var id: Long? = null,
) : BaseEntity() {
    companion object {
        fun from(newSchedule: NewSchedule, scheduleGroupId: Long) = ScheduleEntity(
            title = newSchedule.titleValue,
            description = newSchedule.descriptionValue,
            isAllDay = newSchedule.isAllDay,
            startDateTime = newSchedule.startDateTime,
            endDateTime = newSchedule.endDateTime,
            place = newSchedule.place,
            setLocation = newSchedule.setLocation,
            latitude = newSchedule.latitude,
            longitude = newSchedule.longitude,
            setAlarm = newSchedule.setAlarm,
            scheduleCategoryId = newSchedule.categoryId,
            calendarId = newSchedule.calendarId,
            scheduleGroupId = scheduleGroupId,
        )
    }

    fun toDomain(
        alarms: List<ScheduleAlarmEntity>,
        scheduleCategory: ScheduleCategoryEntity,
        calendar: CalendarEntity,
        isRepeated: Boolean,
        pattern: RepetitionPatternEntity?,
    ) = Schedule.of(
        id = id!!,
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
        alarmTimes = alarms.map(ScheduleAlarmEntity::alarmTime),
        categoryId = scheduleCategoryId,
        categoryColorHex = scheduleCategory.colorHex,
        categoryName = scheduleCategory.name,
        categoryIsDefault = scheduleCategory.isDefault,
        calendarId = calendarId,
        calendarTitle = calendar.title,
        scheduleGroupId = scheduleGroupId,
        memberKey = calendar.memberKey,
        isRepeated = isRepeated,
        repetitionType = pattern?.repetitionType,
        repetitionPeriod = pattern?.repetitionPeriod,
        repetitionStartDate = pattern?.repetitionStartDate,
        repetitionEndDate = pattern?.repetitionEndDate,
        monthOfYear = pattern?.monthOfYear,
        dayOfMonth = pattern?.dayOfMonth,
        weekNumber = pattern?.weekNumber,
        weekday = pattern?.weekday,
        weekdayBit = pattern?.weekdayBit,
    )

    fun update(editSchedule: EditSchedule, scheduleGroupId: Long) {
        this.title = editSchedule.titleValue
        this.description = editSchedule.descriptionValue
        this.isAllDay = editSchedule.isAllDay
        this.startDateTime = editSchedule.startDateTime
        this.endDateTime = editSchedule.endDateTime
        this.place = editSchedule.place
        this.setLocation = editSchedule.setLocation
        this.latitude = editSchedule.latitude
        this.longitude = editSchedule.longitude
        this.setAlarm = editSchedule.setAlarm
        this.scheduleCategoryId = editSchedule.categoryId
        this.scheduleGroupId = scheduleGroupId
    }
}