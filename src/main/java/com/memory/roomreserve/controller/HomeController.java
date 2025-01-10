package com.memory.roomreserve.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
        if (user == null){
            return "redirect:/login";
        } else if (user.getAuthorities().stream().anyMatch(e -> e.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/rooms";
        } else if (user.getAuthorities().stream().anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/rooms";
        }
        return "not-implemented";
    }
}
