package com.didim.dbmain.schedule.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.schedule.domain.ScheduleAlarm
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "schedule_alarm")
@Entity
internal class ScheduleAlarmEntity(
    @Column(nullable = false)
    var alarmTime: LocalDateTime,
    @Column(nullable = false)
    var scheduleId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_alarm_id")
    var id: Long? = null,
): BaseEntity() {
    companion object {
        fun of(scheduleAlarm: ScheduleAlarm, scheduleId: Long) = ScheduleAlarmEntity(
            alarmTime = scheduleAlarm.time,
            scheduleId = scheduleId,
        )
    }
}