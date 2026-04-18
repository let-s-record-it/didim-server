package com.didim.api.auth.service

import com.didim.domain.auth.domain.IdToken
import com.didim.domain.auth.domain.OAuthAccount
import com.didim.domain.auth.domain.OAuthMember
import com.didim.domain.auth.implement.OAuthAuthenticator
import com.didim.domain.member.domain.OAuthProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GoogleAuthenticator(
    private val idTokenValidator: IdTokenValidator,
    private val googleClient: GoogleClient,
) : OAuthAuthenticator {
    companion object {
        private const val ISS = "https://accounts.google.com"
    }

    @Value($$"${app-key.google}")
    private lateinit var appKey: String

    override val provider: OAuthProvider = OAuthProvider.GOOGLE

    override fun authenticate(idToken: IdToken): OAuthAccount {
        idTokenValidator.validateIdToken(idToken, googleClient.getPublicKeys(), ISS, appKey)

        return OAuthAccount(idToken.sub, provider)
    }

    override fun getMemberInfo(accessToken: String): OAuthMember =
        googleClient.getUserInfo(accessToken).toOAuthMember()
}