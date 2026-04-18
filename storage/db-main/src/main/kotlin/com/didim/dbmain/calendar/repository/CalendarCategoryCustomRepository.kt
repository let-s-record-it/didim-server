package com.didim.dbmain.calendar.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.calendar.entity.CalendarCategoryEntity
import com.didim.dbmain.calendar.entity.QCalendarCategoryEntity.calendarCategoryEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class CalendarCategoryCustomRepository : QuerydslRepositorySupport(CalendarCategoryEntity::class) {

    fun findById(id: Long) =
        selectFrom(calendarCategoryEntity)
            .where(
                calendarCategoryEntity.id.eq(id),
                calendarCategoryEntity.status.eq(EntityStatus.ACTIVE)
            )
            .fetchOne()

    fun findByMemberKey(memberKey: String): List<CalendarCategoryEntity> =
        selectFrom(calendarCategoryEntity)
            .where(
                calendarCategoryEntity.memberKey.eq(memberKey),
                calendarCategoryEntity.status.eq(EntityStatus.ACTIVE)
            )
            .fetch()

    fun findDefaultCategoryByMemberKey(memberKey: String) =
        selectFrom(calendarCategoryEntity)
            .where(
                calendarCategoryEntity.memberKey.eq(memberKey),
                calendarCategoryEntity.isDefault.isTrue,
                calendarCategoryEntity.status.eq(EntityStatus.ACTIVE)
            )
            .fetchOne()
}