package com.didim.dbmain.schedule.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.schedule.entity.QScheduleCategoryEntity.scheduleCategoryEntity
import com.didim.dbmain.schedule.entity.ScheduleCategoryEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class ScheduleCategoryCustomRepository : QuerydslRepositorySupport(ScheduleCategoryEntity::class) {

    fun findById(id: Long): ScheduleCategoryEntity? =
        selectFrom(scheduleCategoryEntity)
            .where(
                scheduleCategoryEntity.id.eq(id),
                scheduleCategoryEntity.status.eq(EntityStatus.ACTIVE)
            )
            .fetchOne()

    fun findByCalendarId(calendarId: Long): List<ScheduleCategoryEntity> =
        selectFrom(scheduleCategoryEntity)
            .where(
                scheduleCategoryEntity.calendarId.eq(calendarId),
                scheduleCategoryEntity.status.eq(EntityStatus.ACTIVE)
            )
            .fetch()

    fun findDefaultCategoryByCalendarId(calendarId: Long): ScheduleCategoryEntity? =
        selectFrom(scheduleCategoryEntity)
            .where(
                scheduleCategoryEntity.calendarId.eq(calendarId),
                scheduleCategoryEntity.isDefault.isTrue,
                scheduleCategoryEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetchOne()
}