package com.didim.domain.calendar.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.calendar.dataaccess.CalendarCategoryRepository
import com.didim.domain.calendar.domain.EditCalendarCategory
import com.didim.domain.calendar.domain.NewCalendarCategory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class CalendarCategoryManager(
    private val calendarCategoryRepository: CalendarCategoryRepository,
) {

    @Transactional(readOnly = true)
    fun find(id: Long) = calendarCategoryRepository.findById(id) ?: throw AppException(ErrorType.NOT_FOUND_DATA)

    fun validateOwner(id: Long, memberKey: String) {
        if (!find(id).isOwner(memberKey)) {
            throw AppException(ErrorType.INVALID_CALENDAR_CATEGORY_GET_REQUEST)
        }
    }

    @Transactional(readOnly = true)
    fun findCategories(memberKey: String) = calendarCategoryRepository.findByMemberKey(memberKey)

    @Transactional(readOnly = true)
    fun findDefaultCategory(memberKey: String) = calendarCategoryRepository.findDefaultCategoryByMemberKey(memberKey)
        ?: throw AppException(ErrorType.CALENDAR_CATEGORY_NOT_FOUND)

    fun add(newCalendarCategory: NewCalendarCategory) =
        calendarCategoryRepository.save(newCalendarCategory)

    fun modify(editCalendarCategory: EditCalendarCategory) {
        validateOwner(editCalendarCategory.id, editCalendarCategory.memberKey) // TODO: calendar member도 수정할 수 있도록 변경 필요
        calendarCategoryRepository.update(editCalendarCategory)
    }

    fun remove(categoryId: Long, memberKey: String) {
        validateOwner(categoryId, memberKey) // TODO: calendar member도 삭제할 수 있도록 변경 필요
        calendarCategoryRepository.delete(categoryId)
    }
}