package com.didim.api.auth.service

import com.didim.domain.auth.domain.IdToken
import com.didim.domain.auth.domain.OAuthAccount
import com.didim.domain.auth.domain.OAuthMember
import com.didim.domain.auth.implement.OAuthAuthenticator
import com.didim.domain.member.domain.OAuthProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KakaoAuthenticator(
    private val idTokenValidator: IdTokenValidator,
    private val kakaoClient: KakaoClient,
) : OAuthAuthenticator {
    companion object {
        private const val ISS = "https://kauth.kakao.com"
    }

    @Value($$"${app-key.kakao}")
    private lateinit var appKey: String

    override val provider: OAuthProvider = OAuthProvider.KAKAO

    override fun authenticate(idToken: IdToken): OAuthAccount {
        idTokenValidator.validateIdToken(idToken, kakaoClient.getPublicKeys(), ISS, appKey)

        return OAuthAccount(idToken.sub, provider)
    }

    override fun getMemberInfo(accessToken: String): OAuthMember =
        kakaoClient.getUserInfo(accessToken).toOAuthMember()

}