package com.didim.domain.schedule.implement

import com.didim.domain.alarm.domain.PushAlarm
import com.didim.domain.alarm.domain.vo.JobGroupIdentity
import com.didim.domain.alarm.implement.AlarmScheduler
import com.didim.domain.calendar.implement.CalendarMemberManager
import com.didim.domain.schedule.dataaccess.ScheduleAlarmRepository
import com.didim.domain.schedule.domain.Schedule
import com.didim.domain.schedule.domain.ScheduleAlarm
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ScheduleAlarmManager(
    private val scheduleAlarmRepository: ScheduleAlarmRepository,
    private val calendarMemberManager: CalendarMemberManager,
    private val alarmScheduler: AlarmScheduler,
) {
    fun setAlarm(scheduleAlarms: List<ScheduleAlarm>, schedule: Schedule): List<Long> {
        calendarMemberManager.findCalendarMembers(schedule.calendarId).forEach {
            alarmScheduler.schedule(
                PushAlarm.scheduleAlarmOf(
                    scheduleId = schedule.id,
                    alarmTimes = scheduleAlarms.map(ScheduleAlarm::time),
                    payload = mapOf(),
                    memberKey = it.memberKey,
                    startDateTime = schedule.startDateTime,
                    scheduleTitle = schedule.titleValue,
                )
            )
        }
        return scheduleAlarmRepository.saveAll(scheduleAlarms, schedule.id)
    }

    fun unsetAlarmsForCalendarMembers(scheduleId: Long, calendarId: Long) {
        calendarMemberManager.findCalendarMembers(calendarId).forEach {
            alarmScheduler.unschedule(JobGroupIdentity.scheduleAlarm(it.memberKey, scheduleId))
        }
    }

    fun deleteAlarms(scheduleIds: List<Long>, calendarId: Long) {
        scheduleIds.forEach {
            unsetAlarmsForCalendarMembers(it, calendarId)
        }
        scheduleAlarmRepository.deleteSchedules(scheduleIds)
    }

    fun deleteAlarm(scheduleId: Long, calendarId: Long) {
        unsetAlarmsForCalendarMembers(scheduleId, calendarId)

        scheduleAlarmRepository.delete(scheduleId)
    }
}