package com.Rightsy.demo.security;

import com.Rightsy.demo.Dto.*;
import com.Rightsy.demo.entity.User;
import com.Rightsy.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final Authutil authutil;

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
                .build());

        return modelMapper.map(user,SignupResponseDto.class);

    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword())
            );

            User user=(User)authentication.getPrincipal();
            String token=authutil.generateToken(user);
            return  new LoginResponseDto(token,user.getId());
        }
        catch (BadCredentialsException e){
            throw  new BadCredentialsException("Invalid Credentials");
        }
    }
}
