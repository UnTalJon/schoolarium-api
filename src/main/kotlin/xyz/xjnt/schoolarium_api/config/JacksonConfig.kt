package xyz.xjnt.schoolarium_api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.openapitools.jackson.nullable.JsonNullableModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(JsonNullableModule())
        }
    }
}