package com.didim.dbmain.schedule.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.schedule.entity.QScheduleAlarmEntity.scheduleAlarmEntity
import com.didim.dbmain.schedule.entity.ScheduleAlarmEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
@Transactional
class ScheduleAlarmCustomRepository : QuerydslRepositorySupport(ScheduleAlarmEntity::class) {

    fun delete(scheduleId: Long) {
        flush()

        update(scheduleAlarmEntity)
            .set(scheduleAlarmEntity.status, EntityStatus.DELETED)
            .where(scheduleAlarmEntity.scheduleId.eq(scheduleId))
            .execute()

        clear()
    }

    fun deleteSchedules(scheduleIds: List<Long>) {
        flush()

        update(scheduleAlarmEntity)
            .set(scheduleAlarmEntity.status, EntityStatus.DELETED)
            .set(scheduleAlarmEntity.deletedAt, LocalDateTime.now())
            .where(scheduleAlarmEntity.scheduleId.`in`(scheduleIds))
            .execute()

        clear()
    }
}