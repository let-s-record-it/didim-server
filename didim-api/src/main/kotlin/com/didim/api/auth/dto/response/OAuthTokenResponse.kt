package com.didim.api.auth.dto.response

import com.didim.domain.member.domain.Jwt

data class OAuthTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val activated: Boolean,
) {
    companion object {
        fun of(jwt: Jwt, activated: Boolean) = OAuthTokenResponse(
            accessToken = jwt.accessToken,
            refreshToken = jwt.refreshToken,
            activated = activated,
        )
    }
}