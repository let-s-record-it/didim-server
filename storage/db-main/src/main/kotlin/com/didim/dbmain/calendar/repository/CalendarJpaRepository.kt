package com.didim.dbmain.calendar.repository

import com.didim.dbmain.calendar.entity.CalendarEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface CalendarJpaRepository : JpaRepository<CalendarEntity, Long> {
}