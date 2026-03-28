package com.didim.dbmain.calendar.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "calendar_category")
@Entity
internal class CalendarCategoryEntity(
    @Column(nullable = false)
    var colorHex: String,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var isDefault: Boolean,
    @Column(nullable = false)
    var memberKey: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_category_id")
    var id: Long? = null,
) {
}