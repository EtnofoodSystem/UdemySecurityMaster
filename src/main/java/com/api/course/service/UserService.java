package com.api.course.service;

import com.api.course.dto.SaveUser;
import com.api.course.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registrOneCustomer(SaveUser newUser);
}
