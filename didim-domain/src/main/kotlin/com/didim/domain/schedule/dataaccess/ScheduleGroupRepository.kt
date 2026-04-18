package com.didim.domain.schedule.dataaccess

import com.didim.domain.schedule.domain.ScheduleGroup

interface ScheduleGroupRepository {

    fun save(scheduleGroup: ScheduleGroup): Long

    fun deleteById(id: Long)
}