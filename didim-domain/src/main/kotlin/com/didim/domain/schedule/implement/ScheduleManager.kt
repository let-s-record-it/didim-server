package com.didim.domain.schedule.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.schedule.dataaccess.ScheduleGroupRepository
import com.didim.domain.schedule.dataaccess.ScheduleRepository
import com.didim.domain.schedule.domain.EditSchedule
import com.didim.domain.schedule.domain.NewSchedule
import com.didim.domain.schedule.domain.ScheduleGroup
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Component
@Transactional
class ScheduleManager(
    private val scheduleRepository: ScheduleRepository,
    private val scheduleGroupRepository: ScheduleGroupRepository,
) {

    fun add(newSchedule: NewSchedule): List<Long> {
        val scheduleGroupId = scheduleGroupRepository.save(ScheduleGroup(newSchedule.isRepeated))
        if (newSchedule.isRepeated) {
            return scheduleRepository.saveAll(newSchedule.toRepeatedSchedules(), scheduleGroupId)
        }

        return listOf(scheduleRepository.save(newSchedule, scheduleGroupId))
    }

    @Transactional(readOnly = true)
    fun find(id: Long) = scheduleRepository.findById(id) ?: throw AppException(ErrorType.SCHEDULE_NOT_FOUND)

    @Transactional(readOnly = true)
    fun findScheduleIdsInGroup(scheduleGroupId: Long) =
        scheduleRepository.findScheduleIdsByScheduleGroupId(scheduleGroupId)

    @Transactional(readOnly = true)
    fun findScheduleIdsInGroupAfterThis(scheduleGroupId: Long, dateTime: LocalDateTime) =
        scheduleRepository.findScheduleIdsByScheduleGroupIdAfterThis(scheduleGroupId, dateTime)

    fun findSchedulesInDay(calendarId: Long, date: LocalDate) =
        scheduleRepository.findSchedulesInDay(calendarId, date)

    fun findSchedulesInMonth(calendarId: Long, year: Int, month: Int) =
        scheduleRepository.findSchedulesInMonth(calendarId, year, month)

    fun findSchedulesContainQuery(calendarId: Long, query: String) =
        scheduleRepository.findSchedulesContainQuery(calendarId, query)


    fun modify(editSchedule: EditSchedule, calendarId: Long): List<Long> {
        val newScheduleGroupId = scheduleGroupRepository.save(ScheduleGroup(editSchedule.isRepeated))
        val scheduleIds = mutableListOf(editSchedule.id)

        scheduleRepository.update(editSchedule, newScheduleGroupId)

        if (editSchedule.isRepeated) {
            val savedScheduleIds =
                scheduleRepository.saveAll(editSchedule.toRepeatedSchedules(calendarId), newScheduleGroupId)
            scheduleIds.addAll(savedScheduleIds)
        }
        return scheduleIds
    }

    fun modifyCategories(categoryId: Long, newCategoryId: Long) =
        scheduleRepository.updateCategories(categoryId, newCategoryId)

    fun delete(id: Long) {
        scheduleRepository.deleteById(id)
    }

    fun deleteSchedulesInCalendar(calendarId: Long) =
        scheduleRepository.deleteSchedulesByCalendarId(calendarId)

    fun deleteScheduleGroup(scheduleGroupId: Long) {
        scheduleRepository.deleteByScheduleGroupId(scheduleGroupId)
        scheduleGroupRepository.deleteById(scheduleGroupId)
    }

    fun deleteScheduleGroupIfIsEmpty(scheduleGroupId: Long) {
        if (!scheduleRepository.existsScheduleInGroup(scheduleGroupId)) {
            scheduleGroupRepository.deleteById(scheduleGroupId)
        }
    }
}