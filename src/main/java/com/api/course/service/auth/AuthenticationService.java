package com.api.course.service.auth;

import com.api.course.dto.RegisteredUser;
import com.api.course.dto.SaveUser;
import com.api.course.entity.User;
import com.api.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    public RegisteredUser registerOneCustumer(SaveUser newUser) {
        User user = userService.registrOneCustomer(newUser);

        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());
        String jwt = jwtService.generatedToken(user, generateExtraclaims(user));
        userDto.setJwt(jwt);
        return userDto;
    }

    private Map<String, Object> generateExtraclaims(User user) {
        Map<String, Object> extraclaims = new HashMap<>();
        extraclaims.put("name", user.getId());
        extraclaims.put("role", user.getRole().name());
        extraclaims.put("authorities", user.getAuthorities());
        return extraclaims;
    }


}
