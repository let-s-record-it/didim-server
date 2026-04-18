package com.didim.domain.schedule.business

import com.didim.domain.calendar.implement.CalendarMemberManager
import com.didim.domain.schedule.domain.EditScheduleCategory
import com.didim.domain.schedule.domain.NewScheduleCategory
import com.didim.domain.schedule.domain.ScheduleCategory
import com.didim.domain.schedule.implement.ScheduleCategoryManager
import com.didim.domain.schedule.implement.ScheduleManager
import org.springframework.stereotype.Service

@Service
class ScheduleCategoryService(
    private val calendarMemberManager: CalendarMemberManager,
    private val scheduleCategoryManager: ScheduleCategoryManager,
    private val scheduleManager: ScheduleManager,
) {

    fun findScheduleCategories(calendarId: Long, memberKey: String): List<ScheduleCategory> {
        calendarMemberManager.validateMember(calendarId, memberKey)

        return scheduleCategoryManager.findScheduleCategories(calendarId)
    }

    fun addScheduleCategory(newScheduleCategory: NewScheduleCategory, memberKey: String): Long {
        calendarMemberManager.validateMember(newScheduleCategory.calendarId, memberKey)

        return scheduleCategoryManager.add(newScheduleCategory)
    }

    fun modifyScheduleCategory(editScheduleCategory: EditScheduleCategory, memberKey: String) {
        val scheduleCategory = scheduleCategoryManager.find(editScheduleCategory.id)
        calendarMemberManager.validateMember(scheduleCategory.calendarId, memberKey)

        scheduleCategoryManager.modify(editScheduleCategory)
    }

    fun removeScheduleCategory(id: Long, memberKey: String) {
        val scheduleCategory = scheduleCategoryManager.find(id)
        calendarMemberManager.validateMember(scheduleCategory.calendarId, memberKey)

        scheduleCategoryManager.remove(id)
        scheduleCategoryManager.findDefaultCategory(scheduleCategory.calendarId).let {
            scheduleManager.modifyCategories(id, it.id)
        }
    }
}