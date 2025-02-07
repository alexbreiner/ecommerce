package com.code.craft.ecommerce.infrastructure.configuration;

import com.code.craft.ecommerce.infrastructure.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final UserDetailServiceImpl userDetailService;

    public SecurityConfig(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //Authorities
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
          return httpSecurity
                  .csrf(AbstractHttpConfigurer::disable)
                  .authorizeHttpRequests(authReqConfig -> authReqConfig
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER").anyRequest().permitAll())
                  .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/login/access"))
                  .logout(logout -> logout.logoutSuccessUrl("/close"))
                  .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
