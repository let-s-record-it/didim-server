package com.didim.dbmain.calendar.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.calendar.domain.CalendarCategory
import com.didim.domain.calendar.domain.EditCalendarCategory
import com.didim.domain.calendar.domain.NewCalendarCategory
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
) : BaseEntity() {
    companion object {
        fun from(newCalendarCategory: NewCalendarCategory) = CalendarCategoryEntity(
            colorHex = newCalendarCategory.colorHexValue,
            name = newCalendarCategory.nameValue,
            isDefault = false,
            memberKey = newCalendarCategory.memberKey,
        )
    }

    fun toDomain() = CalendarCategory.of(
        id = id!!,
        colorHex = colorHex,
        name = name,
        isDefault = isDefault,
        memberKey = memberKey,
    )

    fun update(editCalendarCategory: EditCalendarCategory) {
        this.colorHex = editCalendarCategory.colorHexValue
        this.name = editCalendarCategory.nameValue
    }
}