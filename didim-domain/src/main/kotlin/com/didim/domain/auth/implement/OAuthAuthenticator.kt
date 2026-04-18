package com.didim.domain.auth.implement

import com.didim.domain.auth.domain.IdToken
import com.didim.domain.auth.domain.OAuthAccount
import com.didim.domain.auth.domain.OAuthMember
import com.didim.domain.member.domain.OAuthProvider

interface OAuthAuthenticator {
    val provider: OAuthProvider

    fun authenticate(idToken: IdToken): OAuthAccount

    fun getMemberInfo(accessToken: String): OAuthMember
}