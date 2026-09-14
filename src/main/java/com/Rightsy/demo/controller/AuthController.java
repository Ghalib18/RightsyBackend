package com.Rightsy.demo.controller;

import com.Rightsy.demo.Dto.LoginRequestDto;
import com.Rightsy.demo.Dto.LoginResponseDto;
import com.Rightsy.demo.Dto.SignupRequestDto;
import com.Rightsy.demo.Dto.SignupResponseDto;
import com.Rightsy.demo.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto  signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
