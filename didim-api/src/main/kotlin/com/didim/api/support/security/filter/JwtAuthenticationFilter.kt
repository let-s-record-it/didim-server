package com.didim.api.support.security.filter

import com.didim.api.auth.domain.AuthMember
import com.didim.api.auth.enums.TokenType
import com.didim.api.auth.service.JwtValidator
import com.didim.domain.member.implement.MemberFinder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.collections.emptyMap

@Component
class JwtAuthenticationFilter(
    private val jwtValidator: JwtValidator,
    private val memberFinder: MemberFinder,
) : OncePerRequestFilter() {
    companion object {
        private val PERMIT_URLS =
            listOf(
                "/api/v1/auth/",
            )
    }

    override fun shouldNotFilter(request: HttpServletRequest) = PERMIT_URLS.any { request.requestURI.startsWith(it) }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        getTokenFromHeader(request)?.let {
            authenticate(it)
        }

        doFilter(request, response, filterChain)
    }

    private fun getTokenFromHeader(request: HttpServletRequest): String? = request.getHeader(HttpHeaders.AUTHORIZATION)

    private fun authenticate(token: String) {
        val subject =
            jwtValidator.getBearerTokenBody(token).let {
                jwtValidator.getSubjectIfValidWithType(it, TokenType.ACCESS)
            }

        memberFinder.find(subject).run {
            val authorities = roles.map { SimpleGrantedAuthority(it.name) }
            val authMember = AuthMember(this, emptyMap(), authorities)

            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(authMember, null, authorities)
        }
    }
}
