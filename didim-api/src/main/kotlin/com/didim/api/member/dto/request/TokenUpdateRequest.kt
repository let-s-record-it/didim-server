package com.didim.api.member.dto.request

data class TokenUpdateRequest(
    val deviceId: String,
    val fcmToken: String,
)
