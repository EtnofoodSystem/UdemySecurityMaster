package com.api.course.service;

import com.api.course.dto.SaveUser;
import com.api.course.entity.User;
import com.api.course.entity.util.Role;
import com.api.course.exception.InvalidPasswordException;
import com.api.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registrOneCustomer(SaveUser newUser) {
        validationPassword(newUser);
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validationPassword(SaveUser dto) {
        if (!StringUtils.hasText(dto.getPassword())  || !StringUtils.hasText(dto.getRepeatPassword()) ) {
            throw new InvalidPasswordException("Password don't match");
        }
        if (!dto.getPassword().equals(dto.getRepeatPassword()) ) {
            throw new InvalidPasswordException("Password don't match");
        }
    }


}
