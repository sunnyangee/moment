package com.example.moment.config;

import com.example.moment.entity.Progress;
import com.example.moment.service.CustomUserDetailsService;
import com.example.moment.service.ProgressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final ProgressService progressService;

    public SecurityConfig(CustomUserDetailsService uds,
                          ProgressService progressService) {
        this.customUserDetailsService = uds;
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
          // CSRF 끄고
          .csrf(csrf -> csrf.disable())

          // 리소스별 권한
          .authorizeHttpRequests(auth -> auth
            .requestMatchers(
              "/login",
              "/css/**", "/js/**", "/images/**",
              "/error"
            ).permitAll()
            .requestMatchers("/home").hasRole("USER")
            .requestMatchers("/progress/**").hasRole("USER")
            .anyRequest().authenticated()
          )

          // 로그인 페이지 & 동적 성공 핸들러
          .formLogin(form -> form
            .loginPage("/login")
            .successHandler(authenticationSuccessHandler())
            .permitAll()
          )

          // 로그아웃
          .logout(logout -> logout.permitAll())

          // 세션 관리
          .sessionManagement(sess -> sess
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .maximumSessions(1)
            .expiredUrl("/login?expired=true")
          )

          // DAO 프로바이더
          .authenticationProvider(authenticationProvider());

        return http.build();
    }

    /**
     * 로그인 성공 시:
     * - stage == "start" 이면 /home
     * - 그 외면 /progress
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (HttpServletRequest req,
                HttpServletResponse res,
                Authentication auth) -> {

            UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
            Progress prog = progressService.findByUser(principal.getUser());

            String target = "start".equals(prog.getStage())
                          ? "/home"
                          : "/progress";

            res.sendRedirect(req.getContextPath() + target);
        };
    }
}
