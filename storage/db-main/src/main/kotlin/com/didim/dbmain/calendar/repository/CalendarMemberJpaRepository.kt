package com.didim.dbmain.calendar.repository

import com.didim.dbmain.calendar.entity.CalendarMemberEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface CalendarMemberJpaRepository : JpaRepository<CalendarMemberEntity, Long> {
}