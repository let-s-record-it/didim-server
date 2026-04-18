package com.didim.dbmain.schedule.repository

import com.didim.dbmain.schedule.entity.ScheduleCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface ScheduleCategoryJpaRepository : JpaRepository<ScheduleCategoryEntity, Long> {
}