package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.LoguotService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/close")
public class LogoutController {
    private final LoguotService loguotService;

    public LogoutController(LoguotService loguotService) {
        this.loguotService = loguotService;
    }

    @GetMapping
    public String logout(HttpSession httpSession) {
        loguotService.logout(httpSession);

        return "redirect:/home";
    }
}
