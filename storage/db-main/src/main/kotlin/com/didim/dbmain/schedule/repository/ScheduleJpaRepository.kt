package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface ScheduleJpaRepository : JpaRepository<ScheduleEntity, Long> {
}