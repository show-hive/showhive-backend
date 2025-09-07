package com.showhive.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .info(new Info()
                        .title("Showhive API")
                        .description("API for managing showhive")
                        .version("1.0"));
    }

    // 회원 API 그룹
    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder()
                .group("member")
                .pathsToMatch("/member/**")
                .addOpenApiCustomizer(showhiveApiCustomizer())
                .build();
    }

    // 공연 API 그룹
    @Bean
    public GroupedOpenApi performanceApi() {
        return GroupedOpenApi.builder()
                .group("performance")
                .pathsToMatch("/performance/**")
                .addOpenApiCustomizer(showhiveApiCustomizer())
                .build();
    }

    // 예매 API 그룹
    @Bean
    public GroupedOpenApi reservationApi() {
        return GroupedOpenApi.builder()
                .group("reservation")
                .pathsToMatch("/reservation/**")
                .addOpenApiCustomizer(showhiveApiCustomizer())
                .build();
    }

    private OpenApiCustomizer showhiveApiCustomizer() {
        return openApi -> openApi
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
