package com.code.craft.ecommerce.infrastructure.controller;

import com.code.craft.ecommerce.application.service.LoginService;
import com.code.craft.ecommerce.domain.User;
import com.code.craft.ecommerce.infrastructure.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/access")
    public String access(RedirectAttributes attributes, HttpSession httpSession) {
        User user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("idUser").toString()));
        attributes.addFlashAttribute("id", httpSession.getAttribute("idUser").toString());
        if (loginService.existUser(user.getEmail())) {
            if (loginService.getUserType(user.getEmail()).name().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin";
            }
            return "redirect:/home";
        }
        return "redirect:/home";
    }
}
