package com.api.course.controller;

import com.api.course.dto.AuthenticationRequest;
import com.api.course.dto.AuthenticationResponse;
import com.api.course.entity.User;
import com.api.course.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return  ResponseEntity.ok(isTokenValid);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> aunthenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }
    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authenticationService.findLoggerInUser();
        return  ResponseEntity.ok(user);
    }
}
