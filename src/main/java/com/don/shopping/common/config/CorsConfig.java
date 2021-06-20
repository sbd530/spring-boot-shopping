package com.don.shopping.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Vue.js 와의 통신을 위해 Cors 전략에 대한 설정을 합니다.
 * 작성자 : 윤병돈
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // URL Pattern : All
                .allowedOrigins("*") // Origins : All
                .allowedMethods("*") //HTTP Method : All
                .maxAge(3600) //pre-flight Caching
        ;
    }
}
