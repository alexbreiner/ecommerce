package com.code.craft.ecommerce.infrastructure.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //variable atomica
        AtomicReference<String> redirectURL = new AtomicReference<>(request.getContextPath());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDetails.getAuthorities().forEach(
                grantedAuthority -> {
                    System.out.println("grantedAuthority.getAuthority() = " + grantedAuthority.getAuthority());
                    System.out.println("grantedAuthority.getAuthority().equals(\"ADMIN\") = " + grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
                    if (grantedAuthority.getAuthority().equals("ADMIN")) {
                        System.out.println("ingreso al admin");
                        redirectURL.set("/admin");
                    } else {
                        System.out.println("ingreso al home");
                        redirectURL.set("/home");
                    }
                });
        System.out.println("redirectURL.get() = " + redirectURL.get());
        response.sendRedirect(String.valueOf(redirectURL));
    }
}
