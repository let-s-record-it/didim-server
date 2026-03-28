package com.didim.dbmain.invite.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "invite_link")
@Entity
class InviteLinkEntity(
    @Column(nullable = false)
    var inviteCode: String,
    @Column(nullable = false)
    var expiredTime: LocalDateTime,
    @Column(nullable = false)
    var expired: Boolean,
    @Column(nullable = false)
    var calendarId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_link_id")
    var id: Long? = null,
) {
}