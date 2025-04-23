package com.example.moment.config;

import com.example.moment.service.ProgressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class StageInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(StageInterceptor.class);

    private static final List<String> SEQUENCE = List.of(
        "start", "3-2", "3-3", "homeroom", "cafeteria", "nurse-office", "principalsoffice", "1ftoilet", "audiovisualroom", "overnightroom", "counselingroom", "1felevator", "1fstairs", "library", "teachersroom", "computerroom", "clubroom1", "clubroom2", "clubroom3", "artroom", "broadcastingroom", "studentcouncilroom", "bridge", "gym", "gym2f", "clubroom4", "clubroom5", "clubroom6", "stairs2f", "musicroom", "conveniencestore", "clubroom7", "clubroom8", "englishroom", "supplyroom", "scienceroom"
    );

    private final ProgressService progressService;

    public StageInterceptor(ProgressService progressService) {
        this.progressService = progressService;
    }

    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull Object handler
    ) throws Exception {

        String uri = request.getRequestURI();

        // 예외 URL: 로그인, 정적 리소스, 홈, progress
        if (uri.equals("/") ||
            uri.equals("/home") ||
            uri.equals("/progress") ||
            uri.startsWith("/login") ||
            uri.startsWith("/css/") ||
            uri.startsWith("/js/") ||
            uri.startsWith("/images/") ||
            uri.startsWith("/api/")) {
            return true;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() ||
            "anonymousUser".equals(auth.getPrincipal())) {
            return true;
        }

        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        String currentStage = progressService.findByUser(principal.getUser()).getStage();
        String reqStage = uri.startsWith("/") ? uri.substring(1) : uri;

        // 디버그 로그로 상태 찍어보기
        log.debug("▶ 요청URI='{}', 저장된 stage='{}', 요청stage='{}'",
                  uri, currentStage, reqStage);

        int curIdx = SEQUENCE.indexOf(currentStage);
        if (curIdx < 0) curIdx = 0;
        int reqIdx = SEQUENCE.indexOf(reqStage);

        // 이미 완료했거나(<=curIdx), 바로 다음단계(reqIdx == curIdx+1)만 허용
        if (reqIdx >= 0 && reqIdx <= curIdx + 1) {
            return true;
        }

        // 그 외엔 현재 단계로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/" + SEQUENCE.get(curIdx));
        return false;
    }
}
