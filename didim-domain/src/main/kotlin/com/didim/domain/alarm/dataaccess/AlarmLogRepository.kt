package com.didim.domain.alarm.dataaccess

import com.didim.domain.alarm.domain.AlarmLog

interface AlarmLogRepository {

    fun save(alarmLog: AlarmLog): Long
}