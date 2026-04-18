package com.didim.domain.auth.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class IdTokenPayload(
    val iss: String,
    val aud: String,
    val exp: Long,
    val sub: String,
) {
    fun validatePayload(iss: String, aud: String) {
        if (!equalsIss(iss)) {
            throw AppException(ErrorType.ID_TOKEN_UNSUPPORTED)
        }

        if (!equalsAud(aud)) {
            throw AppException(ErrorType.ID_TOKEN_INVALID_KEY)
        }

        if (isValidTime(LocalDateTime.now())) {
            throw AppException(ErrorType.ID_TOKEN_EXPIRED)
        }
    }

    private fun equalsIss(iss: String) = this.iss == iss

    private fun equalsAud(appKey: String) = this.aud == appKey

    private fun isValidTime(standTime: LocalDateTime) =
        LocalDateTime.ofInstant(Instant.ofEpochSecond(exp), ZoneId.systemDefault())
            .isBefore(standTime)
}
