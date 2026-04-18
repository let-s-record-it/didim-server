package com.didim.api.support.serializer

import com.didim.domain.support.JsonSerializer
import org.springframework.stereotype.Component
import tools.jackson.databind.ObjectMapper

@Component
class JacksonJsonSerializer(
    private val objectMapper: ObjectMapper,
) : JsonSerializer {

    override fun <T> serialize(value: T): String =
        objectMapper.writeValueAsString(value)
}
