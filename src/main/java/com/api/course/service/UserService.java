package com.api.course.service;

import com.api.course.dto.SaveUser;
import com.api.course.entity.User;

import java.util.Optional;

public interface  UserService {

    User registrOneCustomer(SaveUser newUser);
    Optional<User> findOneByUsername(String username);
}
