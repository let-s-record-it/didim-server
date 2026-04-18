package com.didim.domain.auth.business

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.auth.domain.NewOAuthLogin
import com.didim.domain.auth.implement.AuthTokenGenerator
import com.didim.domain.auth.implement.OAuthAuthenticator
import com.didim.domain.member.domain.Jwt
import com.didim.domain.member.domain.OAuthProvider
import com.didim.domain.member.implement.MemberManager
import org.springframework.stereotype.Service

@Service
class AuthService(
    authenticators: List<OAuthAuthenticator>,
    private val authTokenGenerator: AuthTokenGenerator,
    private val memberManager: MemberManager,
) {
    private val oAuthAuthenticatorMap: Map<OAuthProvider, OAuthAuthenticator> =
        authenticators.associateBy { it.provider }

    fun login(newOAuthLogin: NewOAuthLogin): Jwt {
        val authenticator = oAuthAuthenticatorMap[newOAuthLogin.provider]
        val oauthAccount = authenticator?.authenticate(newOAuthLogin.idToken)
            ?: throw AppException(ErrorType.DEFAULT_ERROR)

        if (memberManager.exists(oauthAccount)) {
            val memberKey = memberManager.findMemberKey(oauthAccount)
            return authTokenGenerator.generateJwt(memberKey)
        }

        val oAuthMember = authenticator.getMemberInfo(newOAuthLogin.accessToken)
        val memberKey = memberManager.register(oAuthMember.toNewMember())
        return authTokenGenerator.generateJwt(memberKey)
    }

    fun activateMember(personalId: String, memberKey: String) = memberManager.activateMember(personalId, memberKey)

    fun isActivated(memberKey: String) = memberManager.findByMemberKey(memberKey).activated
}