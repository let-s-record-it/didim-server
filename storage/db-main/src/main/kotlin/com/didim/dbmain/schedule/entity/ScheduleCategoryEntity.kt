package com.didim.dbmain.schedule.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.schedule.domain.EditScheduleCategory
import com.didim.domain.schedule.domain.NewScheduleCategory
import com.didim.domain.schedule.domain.ScheduleCategory
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "schedule_category")
@Entity
internal class ScheduleCategoryEntity(
    @Column(nullable = false)
    var colorHex: String,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var isDefault: Boolean,
    @Column(nullable = false)
    var calendarId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) : BaseEntity() {
    companion object {
        fun from(newScheduleCategory: NewScheduleCategory) = ScheduleCategoryEntity(
            colorHex = newScheduleCategory.colorHexValue,
            name = newScheduleCategory.nameValue,
            isDefault = newScheduleCategory.isDefault,
            calendarId = newScheduleCategory.calendarId,
        )
    }

    fun update(editScheduleCategory: EditScheduleCategory) {
        this.colorHex = editScheduleCategory.colorHexValue
        this.name = editScheduleCategory.nameValue
    }

    fun toDomain() = ScheduleCategory.of(
        id = id!!,
        colorHex = colorHex,
        name = name,
        isDefault = isDefault,
        calendarId = calendarId,
    )
}