package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsOpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(
                new Info()
                        .title("REST API - RPG GAME")
                        .description("Jogo de RPG com API Rest no estilo Advanced Dungeons & Dragons (AD&D)")
                        .version("v1")
                        .contact(new Contact().name("Camila Mandai").email("cayumandai@gmail.com"))
        );
    }
}
