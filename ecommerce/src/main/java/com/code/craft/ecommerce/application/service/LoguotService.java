package com.code.craft.ecommerce.application.service;

import jakarta.servlet.http.HttpSession;

public class LoguotService {

    public LoguotService() {}

    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("idUser");
    }

}
