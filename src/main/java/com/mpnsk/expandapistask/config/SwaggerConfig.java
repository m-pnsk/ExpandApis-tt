package com.mpnsk.expandapistask.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("mykola.panasiuk.dev@gmail.com");
        contact.setName("Mykola Panasiuk");

        Info info = new Info()
                .title("Product-app API")
                .version("1.0.0")
                .contact(contact)
                .description("Documentation for REST API endpoints");

        SecurityRequirement bearerAuthentication = new SecurityRequirement().addList("Bearer Authentication");

        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());

        return new OpenAPI()
                .info(info)
                .security(Collections.singletonList(bearerAuthentication))
                .components(components);
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
