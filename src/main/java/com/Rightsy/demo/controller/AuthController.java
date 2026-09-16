package com.Rightsy.demo.controller;

import com.Rightsy.demo.Dto.*;
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

    @PostMapping("verify")
    public ResponseEntity<ApiResponseDto> verify(@RequestBody VerifyEmailRequestDto verifyEmailRequestDto){
        return ResponseEntity.ok(authService.verifyEmail(verifyEmailRequestDto));
    }

    @PostMapping("forgetPassword")
    public ResponseEntity<ApiResponseDto> forgetPassword(@RequestBody ForgetPasswordRequestDto forgetPasswordRequestDto){
        return ResponseEntity.ok(authService.forgetPassword(forgetPasswordRequestDto));
    }

    @PostMapping("verifyOtp")
    public ResponseEntity<ApiResponseDto> verifyOtp(@RequestBody VerifyEmailRequestDto verifyEmailRequestDto){
        return ResponseEntity.ok(authService.verifyCode(verifyEmailRequestDto));
    }
    @PostMapping("resetPassword")
    public ResponseEntity<ApiResponseDto> resetPassword(@RequestBody PasswordResetDto passwordResetDto){
        return ResponseEntity.ok(authService.passwordReset(passwordResetDto));
    }
}
