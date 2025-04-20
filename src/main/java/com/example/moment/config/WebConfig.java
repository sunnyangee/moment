package com.example.moment.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StageInterceptor stageInterceptor;

    public WebConfig(StageInterceptor stageInterceptor) {
        this.stageInterceptor = stageInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(stageInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                    "/", "/login", "/home",
                    "/css/**", "/js/**", "/images/**", "/error"
                );
    }
}
