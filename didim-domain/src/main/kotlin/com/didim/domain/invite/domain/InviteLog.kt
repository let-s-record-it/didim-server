package com.didim.domain.invite.domain

data class InviteLog(
    val inviterId: Long,
    val invitedId: Long,
    val calendarId: Long,
    val state: InviteState,
    val id: Long? = null,
) {
    fun isInvitedMember(memberId: Long) = invitedId == memberId
}
