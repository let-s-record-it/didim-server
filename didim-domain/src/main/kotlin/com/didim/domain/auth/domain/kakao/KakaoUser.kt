package com.didim.domain.auth.domain.kakao

import com.didim.domain.auth.domain.OAuthMember
import com.didim.domain.member.domain.OAuthProvider
import java.time.LocalDateTime

data class KakaoUser(
    val id: Long,
    val connectedAt: LocalDateTime,
    val kakaoAccount: KakaoAccount,
) {
    fun toOAuthMember() = OAuthMember(
        account = id.toString(),
        provider = OAuthProvider.KAKAO,
        name = kakaoAccount.profile.nickname,
        email = kakaoAccount.email,
        profileImageUrl = kakaoAccount.profile.profileImageUrl,
    )
}
