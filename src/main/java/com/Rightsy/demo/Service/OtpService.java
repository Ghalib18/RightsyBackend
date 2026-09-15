package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.OtpType;
import com.Rightsy.demo.entity.VerficationCode;
import com.Rightsy.demo.repository.VerficationCodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final VerficationCodeRepo verficationCodeRepo;
    private VerficationService verficationService;

    public String generateOtpCode(String email, OtpType otpType){

        String otpCode=String.format("%06d", new Random().nextInt(1000000));
        VerficationCode verficationCode=VerficationCode.builder()
                .email(email)
                .code(otpCode)
                .type(otpType)
                .used(false)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();

           verficationCodeRepo.save(verficationCode);
        return otpCode;
    }

    public  void generateAndSendOtp(String email, OtpType otpType){
        String otp=generateOtpCode(email,otpType);
        verficationService.sendOtp(email,otp,otpType);

    }

    @Scheduled(fixedRate=60000)
    public void deleteOtp(){
        verficationCodeRepo.deleteByExpiresAtBefore(LocalDateTime.now());
    }
}
