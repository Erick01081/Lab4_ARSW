package edu.eci.arsw.blueprints.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "edu.eci.arsw.blueprints")
public class AppConfig {

    // Definir un bean para RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}