package com.tenpo.backendchallenge.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend Challenge Reactive API")
                        .version("v0.0.1")
                        .description("API REST reactiva con cálculo dinámico, caché y historial"));
    }
}
