// src/main/java/com/example/moment/controller/ProgressController.java
package com.example.moment.controller;

import com.example.moment.config.UserPrincipal;
import com.example.moment.entity.Progress;
import com.example.moment.entity.User;
import com.example.moment.service.ProgressService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgressController {

  private final ProgressService progressService;

  public ProgressController(ProgressService progressService) {
    this.progressService = progressService;
  }

  @GetMapping("/progress")
  public String progressPage(
      @AuthenticationPrincipal UserPrincipal userPrincipal,
      Model model) {

    User user = userPrincipal.getUser();
    Progress prog = progressService.findByUser(user);
    model.addAttribute("progress", prog);
    return "progress";
  }
}
