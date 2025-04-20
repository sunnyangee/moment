package com.example.moment.controller;

import com.example.moment.config.UserPrincipal;
import com.example.moment.entity.User;
import com.example.moment.service.ProgressService;    // ← 추가
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProgressService progressService;   // ← 추가

    public HomeController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        if (userPrincipal != null) {
            User user = userPrincipal.getUser();
            model.addAttribute("user", user);
        }
        return "home";
    }

    @GetMapping("/3-2")
    public String page3_2(@AuthenticationPrincipal UserPrincipal userPrincipal,
                          Model model) {
        if (userPrincipal != null) {
            User user = userPrincipal.getUser();
            model.addAttribute("user", user);
            // 진행 단계 저장
            progressService.updateStage(user, "3-2");
        }
        return "3-2";
    }

    @GetMapping("/3-3")
    public String page3_3(@AuthenticationPrincipal UserPrincipal userPrincipal,
                          Model model) {
        if (userPrincipal != null) {
            User user = userPrincipal.getUser();
            model.addAttribute("user", user);
            // 진행 단계 저장
            progressService.updateStage(user, "3-3");
        }
        return "3-3";
    }

}
