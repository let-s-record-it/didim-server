package com.didim.domain.schedule.dataaccess

import com.didim.domain.schedule.domain.EditSchedule
import com.didim.domain.schedule.domain.NewSchedule
import com.didim.domain.schedule.domain.Schedule
import java.time.LocalDate
import java.time.LocalDateTime

interface ScheduleRepository {

    fun save(newSchedule: NewSchedule, scheduleGroupId: Long): Long

    fun saveAll(newSchedules: List<NewSchedule>, scheduleGroupId: Long): List<Long>

    fun findById(id: Long): Schedule?

    fun findSchedulesInMonth(calendarId: Long, year: Int, month: Int): List<Schedule>

    fun findSchedulesInDay(calendarId: Long, date: LocalDate): List<Schedule>

    fun findSchedulesContainQuery(calendarId: Long, query: String): List<Schedule>

    fun findScheduleIdsByScheduleGroupId(scheduleGroupId: Long): List<Long>

    fun findScheduleIdsByScheduleGroupIdAfterThis(scheduleGroupId: Long, dateTime: LocalDateTime): List<Long>

    fun existsScheduleInGroup(scheduleGroupId: Long): Boolean

    fun update(editSchedule: EditSchedule, scheduleGroupId: Long)

    fun updateCategories(categoryId: Long, newCategoryId: Long)

    fun deleteById(id: Long)

    fun deleteSchedulesByCalendarId(calendarId: Long)

    fun deleteByScheduleGroupId(scheduleGroupId: Long)
}