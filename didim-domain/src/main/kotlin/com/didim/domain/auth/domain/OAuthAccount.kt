package com.didim.domain.auth.domain

import com.didim.domain.member.domain.OAuthProvider

data class OAuthAccount(
    val account: String,
    val provider: OAuthProvider,
)
