package com.example.moment.controller;

import com.example.moment.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;  // ← 이 라인 추가
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.moment.service.ProgressService;

// src/main/java/com/example/moment/controller/ProgressController.java
@Controller
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/progress")
    public String progressPage(
        @AuthenticationPrincipal UserPrincipal userPrincipal,
        Model model
    ) {
        var user = userPrincipal.getUser();
        var prog = progressService.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("progress", prog);
        return "progress";  // templates/progress.html
    }
}
