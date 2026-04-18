package com.didim.domain.member.domain

data class NewMember(
    val oauthAccount: String,
    val oauthProvider: OAuthProvider,
    val name: String,
    val email: String,
    val profileImageUrl: String,
)
