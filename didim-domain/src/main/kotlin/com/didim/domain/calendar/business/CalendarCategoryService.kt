package com.didim.domain.calendar.business

import com.didim.domain.calendar.domain.EditCalendarCategory
import com.didim.domain.calendar.domain.NewCalendarCategory
import com.didim.domain.calendar.implement.CalendarCategoryManager
import com.didim.domain.calendar.implement.CalendarManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CalendarCategoryService(
    private val calendarCategoryManager: CalendarCategoryManager,
    private val calendarManager: CalendarManager,
) {

    fun findCalendarCategories(memberKey: String) = calendarCategoryManager.findCategories(memberKey)

    fun addCalendarCategory(newCalendarCategory: NewCalendarCategory) {
        calendarCategoryManager.add(newCalendarCategory)
    }

    fun modifyCalendarCategory(editCalendarCategory: EditCalendarCategory) {
        calendarCategoryManager.validateOwner(editCalendarCategory.id, editCalendarCategory.memberKey)

        calendarCategoryManager.modify(editCalendarCategory)
    }

    @Transactional
    fun removeCalendarCategory(id: Long, memberKey: String) {
        calendarCategoryManager.validateOwner(id, memberKey)

        calendarCategoryManager.remove(id, memberKey)
        calendarCategoryManager.findDefaultCategory(memberKey).let {
            calendarManager.modifyCategories(id, it.id)
        }
    }
}