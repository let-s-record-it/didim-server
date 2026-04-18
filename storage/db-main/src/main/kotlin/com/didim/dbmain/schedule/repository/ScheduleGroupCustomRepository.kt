package com.didim.dbmain.schedule.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.schedule.entity.QScheduleGroupEntity.scheduleGroupEntity
import com.didim.dbmain.schedule.entity.ScheduleGroupEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
@Transactional
class ScheduleGroupCustomRepository : QuerydslRepositorySupport(ScheduleGroupEntity::class) {

    fun deleteById(id: Long) {
        flush()

        update(scheduleGroupEntity)
            .set(scheduleGroupEntity.status, EntityStatus.DELETED)
            .set(scheduleGroupEntity.deletedAt, LocalDateTime.now())
            .where(scheduleGroupEntity.id.eq(id))
            .execute()

        clear()
    }
}