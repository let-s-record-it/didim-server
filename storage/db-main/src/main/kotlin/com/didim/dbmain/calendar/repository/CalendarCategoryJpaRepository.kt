package com.didim.dbmain.calendar.repository

import com.didim.dbmain.calendar.entity.CalendarCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface CalendarCategoryJpaRepository: JpaRepository<CalendarCategoryEntity, Long> {
}