package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleCategoryEntity
import com.didim.domain.schedule.dataaccess.ScheduleCategoryRepository
import com.didim.domain.schedule.domain.EditScheduleCategory
import com.didim.domain.schedule.domain.NewScheduleCategory
import com.didim.domain.schedule.domain.ScheduleCategory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class ScheduleCategoryEntityRepository(
    private val scheduleCategoryJpaRepository: ScheduleCategoryJpaRepository,
    private val scheduleCategoryCustomRepository: ScheduleCategoryCustomRepository,
) : ScheduleCategoryRepository {

    override fun save(newScheduleCategory: NewScheduleCategory): Long =
        scheduleCategoryJpaRepository.save(ScheduleCategoryEntity.from(newScheduleCategory)).id!!

    @Transactional(readOnly = true)
    override fun findById(id: Long): ScheduleCategory? =
        scheduleCategoryCustomRepository.findById(id)?.toDomain()

    @Transactional(readOnly = true)
    override fun findByCalendarId(calendarId: Long): List<ScheduleCategory> =
        scheduleCategoryCustomRepository.findByCalendarId(calendarId).map(ScheduleCategoryEntity::toDomain)

    override fun findDefaultCategoryByCalendarId(calendarId: Long): ScheduleCategory? =
        scheduleCategoryCustomRepository.findDefaultCategoryByCalendarId(calendarId)?.toDomain()

    override fun update(editScheduleCategory: EditScheduleCategory) {
        scheduleCategoryCustomRepository.findById(editScheduleCategory.id)?.update(editScheduleCategory)
    }

    override fun delete(id: Long) {
        scheduleCategoryCustomRepository.findById(id)?.delete()
    }
}