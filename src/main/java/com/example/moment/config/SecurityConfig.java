// src/main/java/com/example/moment/config/SecurityConfig.java
package com.example.moment.config;

import com.example.moment.entity.Progress;
import com.example.moment.service.ProgressService;
import com.example.moment.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.http.SessionCreationPolicy;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final ProgressService progressService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          ProgressService progressService) {
        this.customUserDetailsService = customUserDetailsService;
        this.progressService = progressService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          // 1) CSRF 비활성화
          .csrf(csrf -> csrf.disable())
          // 2) URL 권한 설정
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/error").permitAll()
              .requestMatchers("/progress/**").hasRole("USER")
              .requestMatchers("/home").hasRole("USER")
              .anyRequest().authenticated()
          )
          // 3) 로그인 폼 & 성공 핸들러
          .formLogin(form -> form
              .loginPage("/login")
              // 로그인 성공하면 progress 존재 여부로 리다이렉트 분기
              .successHandler(authenticationSuccessHandler())
              .permitAll()
          )
          // 4) 로그아웃
          .logout(logout -> logout.permitAll())
          // 5) 세션 관리
          .sessionManagement(sess -> sess
              .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
              .maximumSessions(1)
              .expiredUrl("/login?expired=true")
          )
          // 6) 인증 프로바이더
          .authenticationProvider(authenticationProvider());

        return http.build();
    }

    /**
     * 로그인 성공 후 호출됩니다.
     * ProgressService 에서 현재 유저의 진행정보를 꺼내 와서
     * stage 가 "start"(또는 아직 기록이 없다면)면 /home,
     * 그 외엔 /progress 로 보냅니다.
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    Authentication authentication
            ) throws java.io.IOException {
                UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
                Progress prog = progressService.findByUser(principal.getUser());
                if (prog == null || "start".equals(prog.getStage())) {
                    response.sendRedirect("/home");
                } else {
                    response.sendRedirect("/progress");
                }
            }
        };
    }
}
