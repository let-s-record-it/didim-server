package com.didim.api.auth.service

import com.didim.common.enums.TokenType
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.auth.implement.AuthTokenGenerator
import com.didim.domain.member.domain.Jwt
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey

const val TOKEN_TYPE_CLAIM = "type"

@Component
class JwtGenerator(
    private val secretKey: SecretKey,
) : AuthTokenGenerator {
    companion object {
        private const val ACCESS_TOKEN_VALIDATION_SECONDS = 60L * 60
        private const val REFRESH_TOKEN_VALIDATION_SECONDS = 60L * 60 * 24 * 30
    }

    override fun generateJwt(memberKey: String): Jwt {
        if (memberKey.isBlank()) {
            throw AppException(ErrorType.INVALID_MEMBER_KEY)
        }

        return Jwt(
            accessToken = buildToken(memberKey, TokenType.ACCESS, ACCESS_TOKEN_VALIDATION_SECONDS),
            refreshToken = buildToken(memberKey, TokenType.REFRESH, REFRESH_TOKEN_VALIDATION_SECONDS),
            memberKey = memberKey,
        )
    }

    private fun buildToken(
        memberKey: String,
        tokenType: TokenType,
        expireSeconds: Long,
    ) = Jwts
        .builder()
        .subject(memberKey)
        .claim(TOKEN_TYPE_CLAIM, tokenType.name)
        .expiration(Date.from(Instant.now().plusSeconds(expireSeconds)))
        .signWith(secretKey)
        .compact()
}
