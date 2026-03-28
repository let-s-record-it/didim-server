package com.didim.dbmain.calendar.repository

import com.didim.dbmain.calendar.entity.CalendarEntity
import com.didim.dbmain.calendar.entity.QCalendarCategoryEntity
import com.didim.dbmain.calendar.entity.QCalendarCategoryEntity.calendarCategoryEntity
import com.didim.dbmain.calendar.entity.QCalendarEntity.calendarEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import com.didim.domain.calendar.domain.Calendar
import com.querydsl.core.types.Projections
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
internal class CalendarCustomRepository : QuerydslRepositorySupport(Calendar::class) {

    fun findById(id: Long): CalendarEntity? =
        selectFrom(calendarEntity)
            .where(calendarEntity.id.eq(id))
            .fetchOne()

    fun findDomainById(id: Long): Calendar? =
        select(
            Projections.constructor(
                Calendar::class.java,
                calendarEntity.id,
                calendarEntity.title,
                calendarEntity.calendarCategoryId,
                calendarCategoryEntity.colorHex,
                calendarEntity.memberKey,
            )
        )
            .from(calendarEntity)
            .where(calendarEntity.id.eq(id))
            .fetchOne()
}