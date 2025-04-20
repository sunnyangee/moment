package com.example.moment.config;


import com.example.moment.entity.Progress;
import com.example.moment.service.ProgressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.lang.NonNull;


@Component
public class StageInterceptor implements HandlerInterceptor {

    private final ProgressService progressService;

    public StageInterceptor(ProgressService progressService) {
        this.progressService = progressService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        String uri = request.getRequestURI();

        // 로그인·정적리소스·홈은 모두 통과
        if (uri.equals("/") ||
            uri.startsWith("/login") ||
            uri.startsWith("/css/") ||
            uri.startsWith("/js/") ||
            uri.startsWith("/images/") ||
            uri.equals("/home")) {
            return true;
        }

        // 인증된 사용자 아니면 pass
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() ||
            "anonymousUser".equals(auth.getPrincipal())) {
            return true;
        }

        // 진행 단계 불러와서 URI와 비교
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        Progress progress = progressService.findByUser(principal.getUser());
        String stageUri = "/" + progress.getStage();

        // 현재 URI가 저장된 단계와 다르면 리다이렉트
        if (!uri.equals(stageUri)) {
            response.sendRedirect(stageUri);
            return false;
        }

        return true;
    }
}
