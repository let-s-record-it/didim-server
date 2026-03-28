package com.didim.domain.calendar.dataaccess

import com.didim.domain.calendar.domain.CalendarCategory

interface CalendarCategoryRepository {

    fun findById(id: Long): CalendarCategory?
}