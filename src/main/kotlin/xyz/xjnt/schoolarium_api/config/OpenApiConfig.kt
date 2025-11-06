package xyz.xjnt.schoolarium_api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Schoolarium API")
                    .description("API for managing students and persons in the Schoolarium system")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("Schoolarium API Support")
                            .email("support@schoolarium.com")
                    )
            )
            .servers(
                listOf(
                    Server().url("http://localhost:8080").description("Local development server"),
                    Server().url("https://schoolarium.xjnt.xyz").description("Production server")
                )
            )
    }
}

