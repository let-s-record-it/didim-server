package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleAlarmEntity
import com.didim.domain.schedule.dataaccess.ScheduleAlarmRepository
import com.didim.domain.schedule.domain.ScheduleAlarm
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class ScheduleAlarmEntityRepository(
    private val scheduleAlarmJpaRepository: ScheduleAlarmJpaRepository,
    private val scheduleAlarmCustomRepository: ScheduleAlarmCustomRepository,
) : ScheduleAlarmRepository {

    override fun saveAll(scheduleAlarms: List<ScheduleAlarm>, scheduleId: Long): List<Long> {
        return scheduleAlarmJpaRepository.saveAll(scheduleAlarms.map { ScheduleAlarmEntity.of(it, scheduleId) })
            .map { it.id!! }
    }

    override fun delete(scheduleId: Long) {
        scheduleAlarmCustomRepository.delete(scheduleId)
    }

    override fun deleteSchedules(scheduleIds: List<Long>) {
        scheduleAlarmCustomRepository.deleteSchedules(scheduleIds)
    }
}