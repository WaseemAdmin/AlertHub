package com.alerthub.loaderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info()
                .title("AlertHub Loader Service API")
                .description("Handles ClickUp, GitHub, and Jira CSV imports")
                .version("1.0"));
    }
}
