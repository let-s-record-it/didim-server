package com.didim.dbmain.calendar.entity

import com.didim.domain.calendar.domain.Calendar
import com.didim.domain.calendar.domain.NewCalendar
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "calendar")
@Entity
internal class CalendarEntity(
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var calendarCategoryId: Long,
    @Column(nullable = false)
    var memberKey: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    var id: Long? = null,
) {
    companion object {
        fun from(calendar: NewCalendar) = CalendarEntity(
            title = calendar.titleValue,
            calendarCategoryId = calendar.categoryId,
            memberKey = calendar.memberKey,
        )
    }

    fun toDomain() = Calendar.of(
        id = id!!,
        title = title,
        categoryId = calendarCategoryId,
        memberKey = memberKey,
    )

    fun update(title: String, categoryId: Long) {
        this.title = title
        this.calendarCategoryId = categoryId
    }
}