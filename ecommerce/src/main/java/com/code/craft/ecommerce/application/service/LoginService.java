package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.domain.User;
import com.code.craft.ecommerce.domain.UserType;
import com.code.craft.ecommerce.infrastructure.dto.UserDto;

public class LoginService {
    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    //retorna true si encuentra el user
    public boolean existUser(String email) {
        try {
            User user = userService.findByEmail(email);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    //Obtenemos el id del usuario
    public Integer getUserId(String email) {
        try {
            return userService.findByEmail(email).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    //Obtener tipo de usuaio
    public UserType getUserType(String email) {
        return userService.findByEmail(email).getUserType();
    }

    // Obtener el usuario por email
    public User getUser(String email) {
        try {
            return userService.findByEmail(email);
        } catch (Exception e) {
            return new User();
        }
    }
    //Obtenemos el user por id
    public User getUser(Integer id) {
        try {
            return userService.findById(id);
        } catch (Exception e) {
            return new User();
        }
    }


}
