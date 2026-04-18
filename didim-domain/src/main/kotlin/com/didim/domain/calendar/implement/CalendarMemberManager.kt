package com.didim.domain.calendar.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.calendar.dataaccess.CalendarMemberRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class CalendarMemberManager(
    private val calendarMemberRepository: CalendarMemberRepository,
) {

    fun add(calendarId: Long, memberKey: String) = calendarMemberRepository.save(calendarId, memberKey)

    fun exists(calendarId: Long, memberKey: String) =
        calendarMemberRepository.existsByCalendarIdAndMemberKey(calendarId, memberKey)

    fun validateMember(calendarId: Long, memberKey: String) {
        if (!exists(calendarId, memberKey)) {
            throw AppException(ErrorType.CALENDAR_MEMBER_NOT_FOUND)
        }
    }

    fun removeCalendarMember(calendarId: Long, memberKey: String) {
        calendarMemberRepository.deleteByCalendarIdAndMemberKey(calendarId, memberKey)
    }

    fun removeCalendarMembersInCalendar(calendarId: Long) {
        calendarMemberRepository.deleteByCalendarId(calendarId)
    }

    fun findCalendarMember(calendarId: Long, memberKey: String) =
        calendarMemberRepository.findByCalendarIdAndMemberKey(calendarId, memberKey)
            ?: throw AppException(ErrorType.CALENDAR_MEMBER_NOT_FOUND)

    fun findCalendarMembers(calendarId: Long) = calendarMemberRepository.findByCalendarId(calendarId)
}