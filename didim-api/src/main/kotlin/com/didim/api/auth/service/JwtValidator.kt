package com.didim.api.auth.service

import com.didim.api.auth.enums.TokenType
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.stereotype.Component
import java.security.SignatureException
import javax.crypto.SecretKey

@Component
class JwtValidator(
    private val secretKey: SecretKey,
) {
    companion object {
        private const val BEARER = "Bearer "
    }

    fun getSubjectIfValidWithType(
        token: String,
        expectedType: TokenType,
    ) = validate(token)
        .payload
        .takeIf { it.get<String>(TOKEN_TYPE_CLAIM) == expectedType.name }
        ?.subject
        ?: throw AppException(ErrorType.INVALID_TOKEN_TYPE)

    fun getBearerTokenBody(token: String): String {
        if (!isBearerToken(token)) {
            throw AppException(ErrorType.INVALID_TOKEN_METHOD)
        }

        return token.removePrefix(BEARER)
    }

    fun validate(token: String): Jws<Claims> =
        try {
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
        } catch (e: Exception) {
            throw when (e) {
                is MalformedJwtException -> AppException(ErrorType.JWT_MALFORMED)
                is UnsupportedJwtException -> AppException(ErrorType.JWT_UNSUPPORTED)
                is ExpiredJwtException -> AppException(ErrorType.JWT_EXPIRED)
                is java.lang.SecurityException, is SignatureException -> AppException(ErrorType.JWT_INVALID_SIGNATURE)
                is IllegalArgumentException -> AppException(ErrorType.JWT_UNSUPPORTED)
                else -> AppException(ErrorType.DEFAULT_ERROR)
            }
        }

    private fun isBearerToken(token: String) = token.startsWith(BEARER)

    private inline fun <reified T> Claims.get(claimName: String): T? = this.get(claimName, T::class.java)
}
