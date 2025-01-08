package com.cha.priceradar.user.controller;

import com.cha.priceradar.user.request.LoginRequest;
import com.cha.priceradar.user.request.SignUpRequest;
import com.cha.priceradar.user.response.TokenInfoResponse;
import com.cha.priceradar.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.singUp(signUpRequest.toDto());
        return "success";
    }

    @PostMapping("/login")
    public TokenInfoResponse login(@RequestBody LoginRequest loginRequest) {

        return userService.login(loginRequest.toDto());
    }
}