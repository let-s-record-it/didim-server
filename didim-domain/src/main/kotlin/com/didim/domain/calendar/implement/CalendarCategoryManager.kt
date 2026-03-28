package com.didim.domain.calendar.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.calendar.dataaccess.CalendarCategoryRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class CalendarCategoryManager(
    private val calendarCategoryRepository: CalendarCategoryRepository,
) {

    fun find(id: Long) = calendarCategoryRepository.findById(id) ?: throw AppException(ErrorType.NOT_FOUND_DATA)
}