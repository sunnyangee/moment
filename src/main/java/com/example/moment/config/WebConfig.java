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
                    "/", "/login", "/home", "/error",
                    "/css/**", "/js/**", "/images/**",
                    "/progress", "/progress/**",   // 진행상황 페이지 제외
                    "/3-2", "/3-3", "/homeroom", "/nurse-office", "/principalsoffice", "1ftoilet", "audiovisualroom", "overnightroom", "counselingroom", "1felevator", "1fstairs", "library", "teachersroom", "computerroom", "clubroom1", "clubroom2", "clubroom3", "artroom", "broadcastingroom", "studentcouncilroom", "bridge", "gym", "gym2f", "clubroom4", "clubroom5", "clubroom6", "stairs2f", "musicroom", "conveniencestore", "clubroom7", "clubroom8", "englishroom", "supplyroom", "scienceroom", "powersupplyroom", "computerserverroom", "pharmacy"   // 각 스테이지 뷰도 제외
                );
    }
}
