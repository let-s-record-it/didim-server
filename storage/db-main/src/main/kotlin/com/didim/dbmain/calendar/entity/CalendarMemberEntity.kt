package com.didim.dbmain.calendar.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.calendar.domain.CalendarMember
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "calendar_member")
@Entity
internal class CalendarMemberEntity(
    @Column(nullable = false)
    var calendarId: Long,
    @Column(nullable = false)
    var memberKey: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_member_id")
    var id: Long? = null,
) : BaseEntity()