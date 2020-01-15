package com.jirath.jirathblog2.conf.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jirath
 */

/**
 * 曾经用过的跨域配置方法，现在使用SpringBoot集成的配置文件
 */
@Configuration
public class CorsConfig {
    @Value("${cors.allowedHeader}")
    String allowedHeader;
    @Value("${cors.allowedOrigins}")
    String allowedOrigins;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowCredentials(true)
                        .allowedMethods("*")
                        .maxAge(3600);
            }
        };
    }
}
