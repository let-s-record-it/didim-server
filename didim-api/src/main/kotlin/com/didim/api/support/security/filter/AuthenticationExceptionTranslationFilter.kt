package com.didim.api.support.security.filter

import com.didim.api.support.security.handler.AuthenticationExceptionHandler
import com.didim.common.exception.AppException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthenticationExceptionTranslationFilter(
    private val exceptionHandler: AuthenticationExceptionHandler,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: AppException) {
            exceptionHandler.handle(request, response, e)
        }
    }
}
