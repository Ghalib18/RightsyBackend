package com.Rightsy.demo.security;

import com.Rightsy.demo.Dto.*;
import com.Rightsy.demo.Service.OtpService;
import com.Rightsy.demo.entity.User;
import com.Rightsy.demo.entity.VerficationCode;
import com.Rightsy.demo.repository.UserRepo;
import com.Rightsy.demo.repository.VerficationCodeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final Authutil authutil;
    private final OtpService otpService;
    private final VerficationCodeRepo verficationCodeRepo;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto){
        User user= userRepo.findByEmail(signupRequestDto.getEmail()).orElse(null);
        if(user!=null){
             throw  new IllegalArgumentException("User Already Exists");
        }
        user=userRepo.save(User.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .role(Role.USER)
                .emailVerified(false)
                .build());
        otpService.generateAndSendOtp(signupRequestDto.getEmail(),OtpType.EmailVerfication);

        return modelMapper.map(user,SignupResponseDto.class);

    }


    public ApiResponseDto verifyEmail(VerifyEmailRequestDto verifyRequest){
        VerficationCode verficationCode=verficationCodeRepo.findTopByEmailAndTypeAndUsedFalseOrderByCreatedAtDesc(verifyRequest.getEmail(),OtpType.EmailVerfication)
                .orElseThrow(()-> new IllegalArgumentException("VerficationCode Not found"));

        if(verficationCode.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("VerficationCode Expired");
        }

        if(!verficationCode.getCode().equals(verifyRequest.getCode())){
            throw new IllegalArgumentException("Invalid verfication code");
        }

       User user =userRepo.findByEmail(verifyRequest.getEmail())
               .orElseThrow(()->new IllegalArgumentException("User Not Found"));
            user.setEmailVerified(true);
            userRepo.save(user);

            verficationCode.setUsed(true);
            verficationCodeRepo.save(verficationCode);

            return new ApiResponseDto("Email Verified Successfully",true);

    }


    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword())
            );

            User user=(User)authentication.getPrincipal();
            if(!user.getEmailVerified()){
                throw  new IllegalArgumentException("Please Verify Your Email first");
            }
            String token=authutil.generateToken(user);
            return  new LoginResponseDto(token,user.getId());
        }
        catch (BadCredentialsException e){
            throw  new BadCredentialsException("Invalid Credentials");
        }
    }

    public ApiResponseDto forgetPassword(ForgetPasswordRequestDto forgetPasswordRequestDto){
        User user= userRepo.findByEmail(forgetPasswordRequestDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("User Not Found"));

        otpService.generateAndSendOtp(forgetPasswordRequestDto.getEmail(),OtpType.ForgotPassword);
        return new ApiResponseDto("Opt has been sent to "+forgetPasswordRequestDto.getEmail(),true);

    }

    public ApiResponseDto verifyCode(VerifyEmailRequestDto verifyEmailRequestDto){

        VerficationCode verficationCode=verficationCodeRepo.findTopByEmailAndTypeAndUsedFalseOrderByCreatedAtDesc(verifyEmailRequestDto.getEmail(),OtpType.ForgotPassword)
                .orElseThrow(()-> new IllegalArgumentException("VerficationCode Not found"));

        if(verficationCode.getExpiresAt().isBefore(LocalDateTime.now())){
            throw  new IllegalArgumentException("VerificationCode Expired");
        }
        if(!verficationCode.getCode().equals(verifyEmailRequestDto.getCode())){
            throw new IllegalArgumentException("Invalid verfication code");
        }

        verficationCode.setVerified(true);
        verficationCodeRepo.save(verficationCode);

        return new ApiResponseDto("Otp has been Verified",true);

    }

    public ApiResponseDto passwordReset(PasswordResetDto passwordResetDto){
        VerficationCode verficationCode=verficationCodeRepo.findTopByEmailAndTypeAndUsedFalseOrderByCreatedAtDesc(passwordResetDto.getEmail(),OtpType.ForgotPassword)
                .orElseThrow(()-> new IllegalArgumentException("VerficationCode Not found"));

        if(!verficationCode.getVerified()){
            throw  new IllegalArgumentException("Otp has Not been verified");
        }

        User user=userRepo.findByEmail(passwordResetDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("User Not Found"));

        user.setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
        userRepo.save(user);
        verficationCode.setUsed(true);
        verficationCodeRepo.save(verficationCode);
        return new ApiResponseDto("Password Reset Successfully",true);
    }
}
