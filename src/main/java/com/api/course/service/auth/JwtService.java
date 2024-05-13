package com.api.course.service.auth;

import com.api.course.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generatedToken(UserDetails user) {
        return null;
    }
}
