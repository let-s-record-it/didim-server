package com.didim.dbmain.invite.entity

import com.didim.domain.invite.domain.InviteState
import jakarta.persistence.*

@Table(name = "invite_log")
@Entity
class InviteLogEntity(
    @Column(nullable = false)
    var inviterKey: String,
    @Column(nullable = false)
    var invitedKey: String,
    @Column(nullable = false)
    var calendarId: Long,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var state: InviteState,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_link_id")
    var id: Long? = null,
) {
}