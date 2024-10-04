package com.journal.journalApp.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class swaggerConfig {

    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI().info(
            new Info().title("Journal App APIs")
                      .description("By Sanya")
        ).servers(Arrays.asList(new Server().url("http://localhost:8081").description("local"),
                                new Server().url("http://localhost:8082").description("live") ))
        .tags(Arrays.asList(
                new Tag().name("Public APIs"),
                new Tag().name("User APIs"),
                new Tag().name("Journal APIs"),
                new Tag().name("Admin APIs")
        ))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new Components().addSecuritySchemes(
             "bearerAuth", new  SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization")
                    
        ));                       
    }

}
