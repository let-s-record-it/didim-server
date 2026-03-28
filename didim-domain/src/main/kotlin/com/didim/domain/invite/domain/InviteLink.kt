package com.didim.domain.invite.domain

import java.time.LocalDateTime

data class InviteLink(
    val inviteCode: String,
    val expiredTime: LocalDateTime,
    val expired: Boolean,
    val calendarId: Long,
    val id: Long? = null,
)
