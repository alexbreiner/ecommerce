package com.code.craft.ecommerce.application.repository;

import com.code.craft.ecommerce.domain.User;

public interface UserRepository {
    User createUser(User user);
    User findByEmail(String email);
    User findById(Integer id);

}
