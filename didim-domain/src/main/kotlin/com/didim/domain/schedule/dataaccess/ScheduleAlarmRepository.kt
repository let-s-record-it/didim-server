package com.didim.domain.schedule.dataaccess

import com.didim.domain.schedule.domain.ScheduleAlarm

interface ScheduleAlarmRepository {
    fun saveAll(scheduleAlarms: List<ScheduleAlarm>, scheduleId: Long): List<Long>

    fun delete(scheduleId: Long)

    fun deleteSchedules(scheduleIds: List<Long>)
}