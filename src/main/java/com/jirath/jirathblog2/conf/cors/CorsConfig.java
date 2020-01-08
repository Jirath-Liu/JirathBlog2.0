package com.jirath.jirathblog2.conf.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jirath
 */
@Configuration
public class CorsConfig {
    @Value("${cors.allowedHeader}")
    String allowedHeader;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedHeaders(allowedHeader)
                        .allowedMethods("*")
                        .maxAge(3600);
            }
        };
    }
}
