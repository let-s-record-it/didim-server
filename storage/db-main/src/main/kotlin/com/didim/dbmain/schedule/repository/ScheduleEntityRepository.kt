package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleEntity
import com.didim.domain.schedule.dataaccess.ScheduleRepository
import com.didim.domain.schedule.domain.EditSchedule
import com.didim.domain.schedule.domain.NewSchedule
import com.didim.domain.schedule.domain.Schedule
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
@Transactional
internal class ScheduleEntityRepository(
    private val scheduleJpaRepository: ScheduleJpaRepository,
    private val scheduleCustomRepository: ScheduleCustomRepository,
) : ScheduleRepository {

    override fun save(newSchedule: NewSchedule, scheduleGroupId: Long): Long =
        scheduleJpaRepository.save(ScheduleEntity.from(newSchedule, scheduleGroupId)).id!!

    override fun saveAll(newSchedules: List<NewSchedule>, scheduleGroupId: Long) =
        scheduleCustomRepository.saveAll(newSchedules, scheduleGroupId)

    override fun findById(id: Long): Schedule? = scheduleCustomRepository.findDomainById(id)

    override fun findSchedulesInMonth(calendarId: Long, year: Int, month: Int): List<Schedule> =
        scheduleCustomRepository.findSchedulesInMonth(calendarId, year, month)

    override fun findSchedulesInDay(calendarId: Long, date: LocalDate): List<Schedule> =
        scheduleCustomRepository.findSchedulesInDay(calendarId, date)

    override fun findSchedulesContainQuery(calendarId: Long, query: String): List<Schedule> =
        scheduleCustomRepository.findSchedulesContainQuery(calendarId, query)

    override fun findScheduleIdsByScheduleGroupId(scheduleGroupId: Long): List<Long> =
        scheduleCustomRepository.findScheduleIdsByScheduleGroupId(scheduleGroupId)

    override fun findScheduleIdsByScheduleGroupIdAfterThis(scheduleGroupId: Long, dateTime: LocalDateTime): List<Long> =
        scheduleCustomRepository.findScheduleIdsByScheduleGroupIdAfterThis(scheduleGroupId, dateTime)

    override fun existsScheduleInGroup(scheduleGroupId: Long): Boolean =
        scheduleCustomRepository.existsScheduleByGroupId(scheduleGroupId)

    override fun update(editSchedule: EditSchedule, scheduleGroupId: Long) {
        scheduleCustomRepository.findById(editSchedule.id)?.update(editSchedule, scheduleGroupId)
    }

    override fun updateCategories(categoryId: Long, newCategoryId: Long) =
        scheduleCustomRepository.updateCategories(categoryId, newCategoryId)

    override fun deleteById(id: Long) =
        scheduleCustomRepository.deleteById(id)

    override fun deleteSchedulesByCalendarId(calendarId: Long) =
        scheduleCustomRepository.deleteByCalendarId(calendarId)

    override fun deleteByScheduleGroupId(scheduleGroupId: Long) =
        scheduleCustomRepository.deleteByScheduleGroupId(scheduleGroupId)
}