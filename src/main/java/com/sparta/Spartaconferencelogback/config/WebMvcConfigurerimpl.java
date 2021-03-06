package com.sparta.Spartaconferencelogback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerimpl implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://leesungmin.shop.s3-website.ap-northeast-2.amazonaws.com/",
                                "http://localhost:3000", "http://localhost:8080")

                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
                .allowCredentials(true);

    }

}
