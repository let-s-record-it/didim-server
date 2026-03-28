package com.didim.dbmain.schedule.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "schedule_group")
@Entity
class ScheduleGroupEntity(
    @Column(nullable = false)
    var isRepeated: Boolean,
    @Column(nullable = false)
    var repetitionPatternId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_group_id")
    var id: Long? = null,
) {
}