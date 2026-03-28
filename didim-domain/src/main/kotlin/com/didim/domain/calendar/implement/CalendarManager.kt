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

    fun create(newCalendar: NewCalendar) = calendarRepository.save(newCalendar)

    fun find(id: Long) = calendarRepository.findById(id) ?: throw AppException(ErrorType.NOT_FOUND_DATA)

    fun modify(editCalendar: EditCalendar) = calendarRepository.update(editCalendar)
}