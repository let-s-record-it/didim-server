package com.didim.domain.calendar.dataaccess

import com.didim.domain.calendar.domain.CalendarCategory
import com.didim.domain.calendar.domain.EditCalendarCategory
import com.didim.domain.calendar.domain.NewCalendarCategory

interface CalendarCategoryRepository {

    fun findById(id: Long): CalendarCategory?

    fun findByMemberKey(memberKey: String): List<CalendarCategory>

    fun findDefaultCategoryByMemberKey(memberKey: String): CalendarCategory?

    fun save(newCalendarCategory: NewCalendarCategory): Long

    fun update(editCalendarCategory: EditCalendarCategory)

    fun delete(categoryId: Long)
}