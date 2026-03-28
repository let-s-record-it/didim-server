package com.didim.domain.calendar.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.calendar.dataaccess.CalendarMemberRepository
import com.didim.domain.calendar.domain.CalendarMember
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class CalendarMemberManager(
    private val calendarMemberRepository: CalendarMemberRepository,
) {

    fun add(calendarMember: CalendarMember) = calendarMemberRepository.save(calendarMember)

    fun exists(calendarId: Long, memberKey: String) =
        calendarMemberRepository.existsByCalendarIdAndMemberKey(calendarId, memberKey)

    fun validateMember(calendarId: Long, memberKey: String) {
        if (!exists(calendarId, memberKey)) {
            throw AppException(ErrorType.CALENDAR_MEMBER_NOT_FOUND)
        }
    }
}