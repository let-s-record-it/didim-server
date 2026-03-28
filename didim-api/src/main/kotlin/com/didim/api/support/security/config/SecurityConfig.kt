package com.didim.api.support.security.config

import com.didim.api.support.security.entrypoint.JwtAuthenticationEntryPoint
import com.didim.api.support.security.filter.AuthenticationExceptionTranslationFilter
import com.didim.api.support.security.filter.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher.withDefaults

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val exceptionTranslationFilter: AuthenticationExceptionTranslationFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
) {

    @Value($$"${client.url}")
    private lateinit var clientUrl: String

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer = WebSecurityCustomizer { web ->
        web.ignoring().requestMatchers("/h2-console/**")
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val mvc = withDefaults()

        http {
            httpBasic { disable() }
            formLogin { disable() }
            logout { disable() }
            csrf { disable() }
            oauth2Login { }

            authorizeHttpRequests {
                authorize(mvc.matcher("/api/v1/auth/sign-in/**"), permitAll)
                authorize(anyRequest, authenticated)
            }

            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }

            exceptionHandling {
                authenticationEntryPoint = jwtAuthenticationEntryPoint
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtAuthenticationFilter)
            addFilterBefore<JwtAuthenticationFilter>(exceptionTranslationFilter)
        }

        return http.build()
    }
}