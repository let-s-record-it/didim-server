package com.didim.domain.schedule.implement

import com.didim.common.enums.InitialColor
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.schedule.dataaccess.ScheduleCategoryRepository
import com.didim.domain.schedule.domain.EditScheduleCategory
import com.didim.domain.schedule.domain.NewScheduleCategory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ScheduleCategoryManager(
    private val scheduleCategoryRepository: ScheduleCategoryRepository,
) {

    fun add(newScheduleCategory: NewScheduleCategory) = scheduleCategoryRepository.save(newScheduleCategory)

    fun addInitialCategories(calendarId: Long, memberKey: String): List<Long> = InitialColor.entries.map {
        val newScheduleCategory = NewScheduleCategory.of(it.colorHex, it.colorName, it.isDefault, calendarId)
        scheduleCategoryRepository.save(newScheduleCategory)
    }

    @Transactional(readOnly = true)
    fun find(id: Long) =
        scheduleCategoryRepository.findById(id) ?: throw AppException(ErrorType.SCHEDULE_CATEGORY_NOT_FOUND)

    fun validateCategoryInCalendar(categoryId: Long, calendarId: Long) {
        val category = find(categoryId)
        if (category.calendarId != calendarId) {
            throw AppException(ErrorType.INVALID_SCHEDULE_CATEGORY_GET_REQUEST)
        }
    }

    @Transactional(readOnly = true)
    fun findScheduleCategories(calendarId: Long) = scheduleCategoryRepository.findByCalendarId(calendarId)

    @Transactional(readOnly = true)
    fun findDefaultCategory(calendarId: Long) =
        scheduleCategoryRepository.findDefaultCategoryByCalendarId(calendarId)
            ?: throw AppException(ErrorType.SCHEDULE_CATEGORY_NOT_FOUND)

    fun modify(editScheduleCategory: EditScheduleCategory) {
        scheduleCategoryRepository.update(editScheduleCategory)
    }

    fun remove(id: Long) {
        scheduleCategoryRepository.delete(id)
    }
}