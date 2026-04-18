package com.didim.dbmain.alarm.repository

import com.didim.dbmain.alarm.entity.AlarmLogEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface AlarmLogJpaRepository : JpaRepository<AlarmLogEntity, Long>