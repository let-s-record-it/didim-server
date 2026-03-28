package com.didim.domain.calendar.dataaccess

import com.didim.domain.calendar.domain.CalendarMember

interface CalendarMemberRepository {
    fun save(calendarMember: CalendarMember): Long

    fun existsByCalendarIdAndMemberKey(calendarId: Long, memberKey: String): Boolean
}