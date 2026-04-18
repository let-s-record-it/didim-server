package com.didim.domain.calendar.dataaccess

import com.didim.domain.calendar.domain.CalendarMember

interface CalendarMemberRepository {
    fun save(calendarId: Long, memberKey: String): Long

    fun existsByCalendarIdAndMemberKey(calendarId: Long, memberKey: String): Boolean

    fun findByCalendarId(calendarId: Long): List<CalendarMember>

    fun findByCalendarIdAndMemberKey(calendarId: Long, memberKey: String): CalendarMember?

    fun deleteByCalendarId(calendarId: Long)

    fun deleteByCalendarIdAndMemberKey(calendarId: Long, memberKey: String)
}