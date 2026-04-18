package com.didim.domain.schedule.dataaccess

import com.didim.domain.schedule.domain.EditScheduleCategory
import com.didim.domain.schedule.domain.NewScheduleCategory
import com.didim.domain.schedule.domain.ScheduleCategory

interface ScheduleCategoryRepository {

    fun save(newScheduleCategory: NewScheduleCategory): Long

    fun findById(id: Long): ScheduleCategory?

    fun findByCalendarId(calendarId: Long): List<ScheduleCategory>

    fun findDefaultCategoryByCalendarId(calendarId: Long): ScheduleCategory?

    fun update(editScheduleCategory: EditScheduleCategory)

    fun delete(id: Long)
}