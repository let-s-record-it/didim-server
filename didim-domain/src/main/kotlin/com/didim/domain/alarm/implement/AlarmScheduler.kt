package com.didim.domain.alarm.implement

import com.didim.domain.alarm.domain.PushAlarm
import com.didim.domain.alarm.domain.vo.JobGroupIdentity

interface AlarmScheduler {
    fun schedule(pushAlarm: PushAlarm)
    fun unschedule(jobGroupIdentity: JobGroupIdentity)
}
