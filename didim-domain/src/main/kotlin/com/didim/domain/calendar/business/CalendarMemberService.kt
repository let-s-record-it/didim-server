package com.didim.domain.calendar.business

import com.didim.domain.calendar.domain.CalendarMember
import com.didim.domain.calendar.implement.CalendarManager
import com.didim.domain.calendar.implement.CalendarMemberManager
import org.springframework.stereotype.Service

@Service
class CalendarMemberService(
    private val calendarMemberManager: CalendarMemberManager,
    private val calendarManager: CalendarManager,
) {

    fun findCalendarMember(calendarId: Long, memberKey: String, requestMemberKey: String): CalendarMember {
        calendarMemberManager.validateMember(calendarId, requestMemberKey)
        return calendarMemberManager.findCalendarMember(calendarId, memberKey)
    }

    fun findCalendarMembers(calendarId: Long, requestMemberKey: String): List<CalendarMember> {
        calendarMemberManager.validateMember(calendarId, requestMemberKey)
        return calendarMemberManager.findCalendarMembers(calendarId)
    }

    fun removeCalendarMember(calendarId: Long, memberKey: String, ownerMemberKey: String) {
        calendarManager.validateOwner(calendarId, ownerMemberKey)
        calendarMemberManager.removeCalendarMember(calendarId, memberKey)
    }
}