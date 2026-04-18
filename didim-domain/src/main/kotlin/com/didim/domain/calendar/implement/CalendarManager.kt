package com.didim.domain.calendar.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.calendar.dataaccess.CalendarRepository
import com.didim.domain.calendar.domain.EditCalendar
import com.didim.domain.calendar.domain.NewCalendar
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class CalendarManager(
    private val calendarRepository: CalendarRepository,
) {

    @Transactional(readOnly = true)
    fun find(id: Long) = calendarRepository.findById(id) ?: throw AppException(ErrorType.NOT_FOUND_DATA)

    @Transactional(readOnly = true)
    fun findCalendars(memberKey: String) = calendarRepository.findByMemberKey(memberKey)

    fun create(newCalendar: NewCalendar) = calendarRepository.save(newCalendar)

    fun modify(editCalendar: EditCalendar) = calendarRepository.update(editCalendar)

    fun modifyCategories(categoryId: Long, newCategoryId: Long) =
        calendarRepository.updateCategories(categoryId, newCategoryId)

    fun remove(calendarId: Long) {
        calendarRepository.delete(calendarId)
    }

    fun validateOwner(id: Long, memberKey: String) {
        if (!find(id).isOwner(memberKey)) {
            throw AppException(ErrorType.INVALID_CALENDAR_GET_REQUEST)
        }
    }
}