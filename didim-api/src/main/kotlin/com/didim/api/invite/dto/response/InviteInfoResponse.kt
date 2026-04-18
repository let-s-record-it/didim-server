package com.didim.api.invite.dto.response

data class InviteInfoResponse(
    val calendarId: Long,
    val calendarTitle: String,
    val ownerId: Long,
    val ownerName: String,
)
