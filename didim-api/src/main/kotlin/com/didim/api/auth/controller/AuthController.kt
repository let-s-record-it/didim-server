package com.didim.api.auth.controller

import com.didim.api.auth.dto.response.OAuthTokenResponse
import com.didim.api.member.dto.request.LoginRequest
import com.didim.api.member.dto.request.WebLoginRequest
import com.didim.api.support.security.annotation.AuthMember
import com.didim.api.support.util.getClientIp
import com.didim.domain.auth.business.AuthService
import com.didim.domain.auth.domain.IdToken
import com.didim.domain.auth.domain.IdTokenHeader
import com.didim.domain.auth.domain.IdTokenPayload
import com.didim.domain.member.domain.Member
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tools.jackson.databind.ObjectMapper
import java.util.*

@RestController
@RequestMapping("/api/v1")
class AuthController(
    private val authService: AuthService,
    private val objectMapper: ObjectMapper,
) {

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest,
        httpRequest: HttpServletRequest
    ): ResponseEntity<OAuthTokenResponse> {
        val jwt = authService.login(
            loginRequest.toNewOAuthLogin(
                idToken = parseToken(loginRequest.idToken),
                ipAddress = getClientIp(httpRequest),
            )
        )
        val activated = authService.isActivated(jwt.memberKey)
        return ResponseEntity.ok(OAuthTokenResponse.of(jwt, activated))
    }

//    @PostMapping("/web-login")
//    fun webLogin(@RequestBody webLoginRequest: WebLoginRequest): ResponseEntity<OAuthTokenResponse> {
//        authService.login(webLoginRequest.exchangeToken)
//        return ResponseEntity.ok()
//    }

    @PostMapping("/activate")
    fun activateMember(
        @Valid @NotBlank @Size(max = 10) @RequestBody personalId: String,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        authService.activateMember(personalId, member.memberKey)

        return ResponseEntity.noContent().build()
    }

    @GetMapping("/auth/validate")
    fun validateToken(): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build()
    }

    private fun parseToken(idToken: String): IdToken {
        val tokenParts = idToken.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return IdToken(
            objectMapper.readValue(
                Base64.getDecoder().decode(tokenParts[0]), IdTokenHeader::class.java
            ),
            objectMapper.readValue(
                Base64.getDecoder().decode(tokenParts[1]), IdTokenPayload::class.java
            ),
            idToken
        )
    }
}