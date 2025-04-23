package com.example.processor.beans.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI processorOpenAPI() {
        return new OpenAPI().info(new Info().title("Processor API").description("Processor service API docs").version("1.0"));
    }
}
