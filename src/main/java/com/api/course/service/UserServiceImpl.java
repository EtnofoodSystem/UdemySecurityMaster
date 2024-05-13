package com.api.course.service;

import com.api.course.dto.SaveUser;
import com.api.course.entity.User;
import com.api.course.entity.util.Role;
import com.api.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User registrOneCustomer(SaveUser newUser) {
        validationPassword(newUser);
        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setRole(Role.ROLE_CUSTOMER);
        return userRepository.save(user);
    }

    private void validationPassword(SaveUser dto) {
        if (!StringUtils.hasText(dto.getPassword())) {
            throw new InvalidPasswordException("Password don't match");
        }
        if (!dto.getPassword().equals(dto.getRepeatPassword()) {
            throw new InvalidPasswordException("Password don't match");
        }
    }


}