package com.didim.domain.calendar.business

import com.didim.domain.calendar.domain.Calendar
import com.didim.domain.calendar.domain.CalendarMember
import com.didim.domain.calendar.domain.EditCalendar
import com.didim.domain.calendar.domain.NewCalendar
import com.didim.domain.calendar.implement.CalendarManager
import com.didim.domain.calendar.implement.CalendarMemberManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CalendarService(
    private val calendarManager: CalendarManager,
    private val calendarMemberManager: CalendarMemberManager,
) {

    @Transactional
    fun createCalendar(newCalendar: NewCalendar): Long {
        // TODO: validate calendar category owner
        val calendarId = calendarManager.create(newCalendar)
        calendarMemberManager.add(CalendarMember(calendarId, newCalendar.memberKey))
        // TODO: initial schedule categories

        return calendarId
    }

    fun modifyCalendar(editCalendar: EditCalendar) {
        calendarMemberManager.validateMember(editCalendar.id, editCalendar.memberKey)
    }
}