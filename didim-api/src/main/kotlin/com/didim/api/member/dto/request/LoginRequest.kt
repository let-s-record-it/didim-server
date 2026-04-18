package com.didim.api.member.dto.request

import com.didim.domain.auth.domain.IdToken
import com.didim.domain.auth.domain.NewOAuthLogin
import com.didim.domain.member.domain.OAuthProvider

data class LoginRequest(
    val idToken: String,
    val accessToken: String,
    val provider: OAuthProvider,
    val deviceId: String,
    val model: String,
    val fcmToken: String,
) {
    fun toNewOAuthLogin(idToken: IdToken, ipAddress: String) = NewOAuthLogin(
        idToken = idToken,
        accessToken = accessToken,
        provider = provider,
        fcmToken = fcmToken,
        deviceId = deviceId,
        model = model,
        ipAddress = ipAddress,
    )
}