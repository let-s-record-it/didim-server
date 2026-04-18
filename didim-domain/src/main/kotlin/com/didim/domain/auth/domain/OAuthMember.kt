package com.didim.domain.auth.domain

import com.didim.domain.member.domain.NewMember
import com.didim.domain.member.domain.OAuthProvider

data class OAuthMember(
    val account: String,
    val provider: OAuthProvider,
    val name: String,
    val email: String,
    val profileImageUrl: String,
) {
    fun toNewMember() = NewMember(
        oauthAccount = account,
        oauthProvider = provider,
        name = name,
        email = email,
        profileImageUrl = profileImageUrl,
    )
}