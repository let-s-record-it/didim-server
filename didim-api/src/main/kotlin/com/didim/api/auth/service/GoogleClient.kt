package com.didim.api.auth.service

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.auth.domain.OidcPublicKeys
import com.didim.domain.auth.domain.google.GoogleUser
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class GoogleClient(
    private val googleRestClient: RestClient,
) {

    companion object {
        private const val PUBLIC_KEY_URL = "https://www.googleapis.com/oauth2/v3/certs"
        private const val USER_INFO_URL = "https://openidconnect.googleapis.com/v1/userinfo"
        private const val BEARER = "Bearer "
    }

    @Cacheable(cacheNames = ["googlePublicKeys"])
    fun getPublicKeys() = runCatching {
        googleRestClient.get()
            .uri(PUBLIC_KEY_URL)
            .retrieve()
            .body<OidcPublicKeys>()
    }.getOrNull() ?: throw AppException(ErrorType.DEFAULT_ERROR)

    fun getUserInfo(accessToken: String) = runCatching {
        googleRestClient.get()
            .uri(USER_INFO_URL)
            .header(HttpHeaders.AUTHORIZATION, "$BEARER$accessToken")
            .retrieve()
            .body<GoogleUser>()
    }.getOrNull() ?: throw AppException(ErrorType.DEFAULT_ERROR)
}