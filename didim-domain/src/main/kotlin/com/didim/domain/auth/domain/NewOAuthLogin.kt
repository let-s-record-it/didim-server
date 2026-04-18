package com.didim.domain.auth.domain

import com.didim.domain.member.domain.OAuthProvider

data class NewOAuthLogin(
    val idToken: IdToken,
    val accessToken: String,
    val provider: OAuthProvider,
    val fcmToken: String,
    val deviceId: String,
    val model: String,
    val ipAddress: String,
)
