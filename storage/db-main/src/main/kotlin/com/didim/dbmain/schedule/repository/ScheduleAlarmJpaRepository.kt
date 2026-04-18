package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleAlarmEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface ScheduleAlarmJpaRepository : JpaRepository<ScheduleAlarmEntity, Long> {
}