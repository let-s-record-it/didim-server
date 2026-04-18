package com.didim.dbmain.calendar.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.calendar.entity.CalendarEntity
import com.didim.dbmain.calendar.entity.QCalendarCategoryEntity.calendarCategoryEntity
import com.didim.dbmain.calendar.entity.QCalendarEntity.calendarEntity
import com.didim.dbmain.calendar.entity.QCalendarMemberEntity.calendarMemberEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import com.didim.domain.calendar.domain.Calendar
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
internal class CalendarCustomRepository : QuerydslRepositorySupport(CalendarEntity::class) {

    fun findById(id: Long): CalendarEntity? =
        selectFrom(calendarEntity)
            .where(calendarEntity.id.eq(id))
            .fetchOne()

    fun findDomainById(id: Long): Calendar? {
        val row = queryFactory.select(
            calendarEntity.id,
            calendarEntity.title,
            calendarEntity.calendarCategoryId,
            calendarCategoryEntity.colorHex,
            calendarEntity.memberKey,
        )
            .from(calendarEntity)
            .innerJoin(calendarCategoryEntity)
            .on(calendarEntity.calendarCategoryId.eq(calendarCategoryEntity.id))
            .where(
                calendarEntity.id.eq(id),
                calendarEntity.status.eq(EntityStatus.ACTIVE)
            ).fetchOne() ?: return null

        return Calendar.of(
            id = row.get(calendarEntity.id)!!,
            title = row.get(calendarEntity.title)!!,
            categoryId = row.get(calendarEntity.calendarCategoryId)!!,
            categoryColorHex = row.get(calendarCategoryEntity.colorHex)!!,
            memberKey = row.get(calendarEntity.memberKey)!!
        )
    }

    fun findByMemberKey(memberKey: String): List<Calendar> =
        queryFactory.select(
            calendarEntity.id,
            calendarEntity.title,
            calendarEntity.calendarCategoryId,
            calendarCategoryEntity.colorHex,
            calendarEntity.memberKey,
        )
            .from(calendarEntity)
            .innerJoin(calendarCategoryEntity)
            .on(calendarEntity.calendarCategoryId.eq(calendarCategoryEntity.id))
            .innerJoin(calendarMemberEntity)
            .on(calendarEntity.id.eq(calendarMemberEntity.calendarId))
            .where(
                calendarMemberEntity.memberKey.eq(memberKey),
                calendarEntity.status.eq(EntityStatus.ACTIVE)
            ).fetch()
            .map {
                Calendar.of(
                    id = it.get(calendarEntity.id)!!,
                    title = it.get(calendarEntity.title)!!,
                    categoryId = it.get(calendarEntity.calendarCategoryId)!!,
                    categoryColorHex = it.get(calendarCategoryEntity.colorHex)!!,
                    memberKey = it.get(calendarEntity.memberKey)!!
                )
            }

    fun updateCategories(categoryId: Long, newCategoryId: Long) {
        flush()

        update(calendarEntity)
            .set(calendarEntity.calendarCategoryId, newCategoryId)
            .where(
                calendarEntity.calendarCategoryId.eq(categoryId),
                calendarEntity.status.eq(EntityStatus.ACTIVE)
            )
            .execute()

        clear()
    }
}