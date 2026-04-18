package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleGroupEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface ScheduleGroupJpaRepository : JpaRepository<ScheduleGroupEntity, Long> {
}