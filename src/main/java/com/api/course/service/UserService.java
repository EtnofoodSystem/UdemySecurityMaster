package com.api.course.service;

import com.api.course.dto.SaveUser;
import com.api.course.entity.User;

public interface  UserService {

    User registrOneCustomer(SaveUser newUser);
}
