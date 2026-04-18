package com.didim.api.auth.service

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.auth.domain.OidcPublicKeys
import com.didim.domain.auth.domain.kakao.KakaoUser
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class KakaoClient(
    private val kakaoRestClient: RestClient,
) {
    companion object {
        const val PUBLIC_KEY_URL = "https://kauth.kakao.com/.well-known/jwks.json"
        private const val USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"
        private const val BEARER = "Bearer "
    }

    @Cacheable(cacheNames = ["kakaoPublicKeys"])
    fun getPublicKeys() = runCatching {
        kakaoRestClient.get()
            .uri(PUBLIC_KEY_URL)
            .retrieve()
            .body<OidcPublicKeys>()
    }.getOrNull() ?: throw AppException(ErrorType.DEFAULT_ERROR)

    fun getUserInfo(accessToken: String) = runCatching {
        kakaoRestClient.get()
            .uri(USER_INFO_URL)
            .header(HttpHeaders.AUTHORIZATION, "$BEARER$accessToken")
            .retrieve()
            .body<KakaoUser>()
    }.getOrNull() ?: throw AppException(ErrorType.DEFAULT_ERROR)
}