package com.example.TeamTracker.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Setting this File as a Configuration , so we can Change WebMvcConfigurer
@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer
{
    //Overriding method from WebMvcConfigurer Int
    @Override
    //Changing default CorsRegistry
    public void addCorsMappings(CorsRegistry registry) {
        //Allowing GET,POST methods from FE trough port 3000,to all endpoints within localhost:8080
        registry.addMapping("http://localhost:8080/**")
                .allowedMethods("GET", "POST").allowedOrigins("http://localhost:3000");
    }
}
