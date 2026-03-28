package com.didim.dbmain.schedule.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "schedule")
@Entity
class ScheduleEntity(
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var description: String,
    @Column(nullable = false)
    var isAllDay: Boolean,
    @Column(nullable = false)
    var startDateTime: LocalDateTime,
    @Column(nullable = false)
    var endDateTime: LocalDateTime,
    @Column(nullable = false)
    var place: String,
    @Column(nullable = false)
    var setLocation: Boolean,
    @Column
    var latitude: Double,
    @Column
    var longitude: Double,
    @Column(nullable = false)
    var setAlarm: Boolean,
    @Column(nullable = false)
    var scheduleCategoryId: Long,
    @Column(nullable = false)
    var calendarId: Long,
    @Column(nullable = false)
    var scheduleGroupId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    var id: Long? = null,
) {
}