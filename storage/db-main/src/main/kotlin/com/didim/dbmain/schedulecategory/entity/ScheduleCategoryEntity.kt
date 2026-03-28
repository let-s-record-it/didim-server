package com.didim.dbmain.schedulecategory.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "schedule_category")
@Entity
class ScheduleCategoryEntity(
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
) {

    fun modify(colorHex: String, name: String) {
        this.name = name
        this.colorHex = colorHex
    }
}