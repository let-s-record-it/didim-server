package com.didim.domain.auth.domain.google

import com.didim.domain.auth.domain.OAuthMember
import com.didim.domain.member.domain.OAuthProvider

data class GoogleUser(
    val sub: String,
    val name: String,
    val givenName: String,
    val picture: String,
    val email: String,
    val emailVerified: Boolean,
    val locale: String,
) {
    fun toOAuthMember() = OAuthMember(
        account = sub,
        provider = OAuthProvider.GOOGLE,
        name = name,
        email = email,
        profileImageUrl = picture,
    )
}
