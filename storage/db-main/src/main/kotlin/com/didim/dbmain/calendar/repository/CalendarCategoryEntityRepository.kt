package com.didim.dbmain.calendar.repository

import com.didim.dbmain.calendar.entity.CalendarCategoryEntity
import com.didim.domain.calendar.dataaccess.CalendarCategoryRepository
import com.didim.domain.calendar.domain.CalendarCategory
import com.didim.domain.calendar.domain.EditCalendarCategory
import com.didim.domain.calendar.domain.NewCalendarCategory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class CalendarCategoryEntityRepository(
    private val calendarCategoryJpaRepository: CalendarCategoryJpaRepository,
    private val calendarCategoryCustomRepository: CalendarCategoryCustomRepository,
) : CalendarCategoryRepository {

    @Transactional(readOnly = true)
    override fun findById(id: Long): CalendarCategory? = calendarCategoryCustomRepository.findById(id)?.toDomain()

    @Transactional(readOnly = true)
    override fun findByMemberKey(memberKey: String): List<CalendarCategory> =
        calendarCategoryCustomRepository.findByMemberKey(memberKey).map(CalendarCategoryEntity::toDomain)

    @Transactional(readOnly = true)
    override fun findDefaultCategoryByMemberKey(memberKey: String): CalendarCategory? =
        calendarCategoryCustomRepository.findDefaultCategoryByMemberKey(memberKey)?.toDomain()

    override fun save(newCalendarCategory: NewCalendarCategory): Long =
        calendarCategoryJpaRepository.save(CalendarCategoryEntity.from(newCalendarCategory)).id!!

    override fun update(editCalendarCategory: EditCalendarCategory) {
        calendarCategoryCustomRepository.findById(editCalendarCategory.id)?.update(editCalendarCategory)
    }

    override fun delete(categoryId: Long) {
        calendarCategoryCustomRepository.findById(categoryId)?.delete()
    }
}