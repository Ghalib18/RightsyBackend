package com.Rightsy.demo.security;

import com.Rightsy.demo.Dto.Role;
import com.Rightsy.demo.entity.User;
import com.Rightsy.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInit implements CommandLineRunner {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Value("${admin.name}")
    private String name;
    @Value("${admin.email}")
    private String email;
    @Value("${admin.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        User user= userRepo.findByEmail(email).orElse(null);

        if(user==null){
            user= User.builder()
                    .name(name)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(Role.ADMIN)
                    .build();
            userRepo.save(user);
        }

    }
}
