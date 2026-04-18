package com.didim.domain.alarm.domain.vo

import com.didim.domain.alarm.domain.AlarmType

@JvmInline
value class JobGroupIdentity private constructor(val value: String) {
    companion object {
        fun scheduleAlarm(memberKey: String, scheduleId: Long) =
            JobGroupIdentity("${AlarmType.SCHEDULE_ALARM.name}-$memberKey-$scheduleId")
    }
}