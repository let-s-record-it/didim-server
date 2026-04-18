package com.didim.domain.calendar.dataaccess

import com.didim.domain.calendar.domain.Calendar
import com.didim.domain.calendar.domain.EditCalendar
import com.didim.domain.calendar.domain.NewCalendar

interface CalendarRepository {

    fun save(newCalendar: NewCalendar): Long
    fun findById(id: Long): Calendar?
    fun findByMemberKey(memberKey: String): List<Calendar>
    fun update(editCalendar: EditCalendar)
    fun updateCategories(categoryId: Long, newCategoryId: Long)
    fun delete(calendarId: Long)
}