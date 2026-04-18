package com.didim.api.support.restclient.config

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter
import org.springframework.web.client.RestClient
import tools.jackson.databind.PropertyNamingStrategies
import tools.jackson.databind.json.JsonMapper
import java.time.Duration

@Configuration
class RestClientConfig {
    companion object {
        private const val API_CONNECT_TIMEOUT_SECONDS = 5L
        private const val API_READ_TIMEOUT_SECONDS = 3L
    }

    @Bean
    fun restClient(): RestClient =
        RestClient
            .builder()
            .requestFactory(createFactory())
            .defaultStatusHandler(
                { status -> status.isError },
                { _, response ->
                    throw AppException(ErrorType.DEFAULT_ERROR, response)
                },
            ).build()

    @Bean
    fun kakaoRestClient(): RestClient = createSnakeCaseRestClient()

    @Bean
    fun googleRestClient(): RestClient = createSnakeCaseRestClient()

    private fun createSnakeCaseRestClient(): RestClient =
        RestClient
            .builder()
            .requestFactory(createFactory())
            .configureMessageConverters { builder ->
                builder.withJsonConverter(
                    JacksonJsonHttpMessageConverter(
                        JsonMapper.builder()
                            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                            .findAndAddModules()
                            .build()
                    )
                )
            }
            .defaultStatusHandler(
                { status -> status.isError },
                { _, response ->
                    throw AppException(ErrorType.DEFAULT_ERROR, response)
                },
            ).build()

    private fun createFactory() =
        SimpleClientHttpRequestFactory().apply {
            setConnectTimeout(Duration.ofSeconds(API_CONNECT_TIMEOUT_SECONDS))
            setReadTimeout(Duration.ofSeconds(API_READ_TIMEOUT_SECONDS))
        }
}
