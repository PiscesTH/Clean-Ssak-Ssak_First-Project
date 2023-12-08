package com.clean.cleanssakssak.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("청소하자 Ver.1")
                        .description("청소 일정 관리 앱 코딩")
                        .version("1.0.0")
                );
    }

}
