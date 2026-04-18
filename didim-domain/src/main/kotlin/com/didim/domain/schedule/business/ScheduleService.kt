package com.didim.domain.schedule.business

import com.didim.domain.calendar.implement.CalendarMemberManager
import com.didim.domain.schedule.domain.EditSchedule
import com.didim.domain.schedule.domain.NewSchedule
import com.didim.domain.schedule.domain.Schedule
import com.didim.domain.schedule.implement.ScheduleAlarmManager
import com.didim.domain.schedule.implement.ScheduleCategoryManager
import com.didim.domain.schedule.implement.ScheduleManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ScheduleService(
    private val calendarMemberManager: CalendarMemberManager,
    private val scheduleCategoryManager: ScheduleCategoryManager,
    private val scheduleManager: ScheduleManager,
    private val scheduleAlarmManager: ScheduleAlarmManager,
) {

    @Transactional
    fun addSchedules(newSchedule: NewSchedule): List<Schedule> {
        calendarMemberManager.validateMember(newSchedule.calendarId, newSchedule.memberKey)
        scheduleCategoryManager.validateCategoryInCalendar(newSchedule.categoryId, newSchedule.calendarId)

        val scheduleIds = scheduleManager.add(newSchedule)

        return scheduleIds.map { scheduleId ->
            scheduleManager.find(scheduleId).also {
                scheduleAlarmManager.setAlarm(newSchedule.alarmTimes, it)
            }
        }
    }

    fun findSchedule(scheduleId: Long, memberKey: String): Schedule {
        val schedule = scheduleManager.find(scheduleId)
        calendarMemberManager.validateMember(schedule.calendarId, memberKey)

        return schedule
    }

    fun findSchedulesInMonth(calendarId: Long, year: Int, month: Int, memberKey: String): List<Schedule> {
        calendarMemberManager.validateMember(calendarId, memberKey)

        return scheduleManager.findSchedulesInMonth(calendarId, year, month)
    }

    fun findSchedulesInDay(calendarId: Long, date: LocalDate, memberKey: String): List<Schedule> {
        calendarMemberManager.validateMember(calendarId, memberKey)

        return scheduleManager.findSchedulesInDay(calendarId, date)
    }

    fun findSchedulesContainQuery(calendarId: Long, query: String, memberKey: String): List<Schedule> {
        calendarMemberManager.validateMember(calendarId, memberKey)

        return scheduleManager.findSchedulesContainQuery(calendarId, query)
    }

    @Transactional
    fun modifySchedule(editSchedule: EditSchedule) {
        val schedule = scheduleManager.find(editSchedule.id)
        calendarMemberManager.validateMember(schedule.calendarId, editSchedule.memberKey)
        scheduleCategoryManager.validateCategoryInCalendar(editSchedule.categoryId, schedule.calendarId)

        val scheduleIds = scheduleManager.modify(editSchedule, schedule.calendarId)

        scheduleIds.forEach {
            val schedule = scheduleManager.find(it)
            scheduleAlarmManager.setAlarm(editSchedule.alarmTimes, schedule)
        }

        scheduleAlarmManager.deleteAlarm(editSchedule.id, schedule.calendarId)
        scheduleManager.deleteScheduleGroupIfIsEmpty(schedule.scheduleGroupId)
    }

    @Transactional
    fun modifySchedulesInGroup(editSchedule: EditSchedule) {
        val schedule = scheduleManager.find(editSchedule.id)
        calendarMemberManager.validateMember(schedule.calendarId, editSchedule.memberKey)
        scheduleCategoryManager.validateCategoryInCalendar(editSchedule.categoryId, schedule.calendarId)

        val scheduleIds = scheduleManager.modify(editSchedule, schedule.calendarId)

        scheduleIds.forEach {
            val schedule = scheduleManager.find(it)
            scheduleAlarmManager.setAlarm(editSchedule.alarmTimes, schedule)
        }

        scheduleAlarmManager.deleteAlarm(editSchedule.id, schedule.calendarId)
        scheduleManager.deleteScheduleGroup(schedule.scheduleGroupId)

    }

    fun removeSchedule(scheduleId: Long, memberKey: String) {
        val schedule = scheduleManager.find(scheduleId)
        calendarMemberManager.validateMember(schedule.calendarId, memberKey)

        scheduleManager.delete(scheduleId)
        scheduleManager.deleteScheduleGroupIfIsEmpty(schedule.scheduleGroupId)

        scheduleAlarmManager.deleteAlarm(scheduleId, schedule.calendarId)
    }

    fun removeSchedulesInGroup(scheduleId: Long, memberKey: String) {
        val schedule = scheduleManager.find(scheduleId)
        calendarMemberManager.validateMember(schedule.calendarId, memberKey)

        scheduleManager.deleteScheduleGroup(schedule.scheduleGroupId)

        val scheduleIdsInGroup = scheduleManager.findScheduleIdsInGroup(schedule.scheduleGroupId)
        scheduleAlarmManager.deleteAlarms(scheduleIdsInGroup, schedule.calendarId)
    }

    fun removeSchedulesInGroupAfterThis(scheduleId: Long, memberKey: String) {
        val schedule = scheduleManager.find(scheduleId)
        calendarMemberManager.validateMember(schedule.calendarId, memberKey)

        val scheduleIdsInGroup =
            scheduleManager.findScheduleIdsInGroupAfterThis(schedule.scheduleGroupId, schedule.startDateTime)

        scheduleIdsInGroup.forEach(scheduleManager::delete)
        scheduleAlarmManager.deleteAlarms(scheduleIdsInGroup, schedule.calendarId)
    }
}