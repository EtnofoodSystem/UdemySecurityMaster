package com.api.course.service.auth;

import com.api.course.dto.RegisteredUser;
import com.api.course.dto.SaveUser;
import com.api.course.entity.User;
import com.api.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String jwt = jwtService.generatedToken(user);
        userDto.setJwt(jwt);
        return userDto;
    }
}
