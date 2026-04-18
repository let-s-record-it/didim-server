package com.didim.domain.calendar.business

import com.didim.domain.calendar.domain.EditCalendar
import com.didim.domain.calendar.domain.NewCalendar
import com.didim.domain.calendar.implement.CalendarCategoryManager
import com.didim.domain.calendar.implement.CalendarManager
import com.didim.domain.calendar.implement.CalendarMemberManager
import com.didim.domain.schedule.implement.ScheduleCategoryManager
import com.didim.domain.schedule.implement.ScheduleManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CalendarService(
    private val calendarManager: CalendarManager,
    private val calendarMemberManager: CalendarMemberManager,
    private val calendarCategoryManager: CalendarCategoryManager,
    private val scheduleCategoryManager: ScheduleCategoryManager,
    private val scheduleManager: ScheduleManager,
) {

    fun find(calendarId: Long) = calendarManager.find(calendarId)

    fun findCalendars(memberKey: String) = calendarManager.findCalendars(memberKey)

    @Transactional
    fun createCalendar(newCalendar: NewCalendar): Long {
        calendarCategoryManager.validateOwner(newCalendar.categoryId, newCalendar.memberKey)

        return calendarManager.create(newCalendar).also {
            calendarMemberManager.add(it, newCalendar.memberKey)
            scheduleCategoryManager.addInitialCategories(it, newCalendar.memberKey)
        }
    }

    fun modifyCalendar(editCalendar: EditCalendar) {
        calendarMemberManager.validateMember(editCalendar.id, editCalendar.memberKey)

        calendarManager.modify(editCalendar)
    }

    @Transactional
    fun removeCalendar(calendarId: Long, ownerKey: String) {
        calendarManager.validateOwner(calendarId, ownerKey)

        calendarMemberManager.removeCalendarMembersInCalendar(calendarId)
        scheduleManager.deleteSchedulesInCalendar(calendarId)

        calendarManager.remove(calendarId)
    }

    fun joinInCalendar(inviteCode: String, joinMemberKey: String) {
        TODO("Not Implement Yet")
    }
}