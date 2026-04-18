package com.didim.dbmain.schedule.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.schedule.domain.ScheduleGroup
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "schedule_group")
@Entity
internal class ScheduleGroupEntity(
    @Column(nullable = false)
    var isRepeated: Boolean,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_group_id")
    var id: Long? = null,
) : BaseEntity() {
    companion object {
        fun from(scheduleGroup: ScheduleGroup) = ScheduleGroupEntity(scheduleGroup.isRepeated)
    }
}