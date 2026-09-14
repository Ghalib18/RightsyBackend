package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.OtpType;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Emailverfication implements VerficationService{

    private final JavaMailSender mailSender;

    @Override
    public void sendOtp(String email, String code, OtpType otpType) {
        String subject;
        String text;

        if(otpType==OtpType.EmailVerfication){
            subject = "Verify your Rightsy account";

            text = "Your Rightsy verification code is: " + code
                    + "\n\nThis code is valid for 15 minutes.";
        }
        else {
            subject = "Rightsy Forgot Password OTP";

            text = "Your Rightsy Forgot Password OTP is: " + code
                    + "\n\nThis code is valid for 15 minutes.";
        }

        SimpleMailMessage message=new SimpleMailMessage();

        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);

    }
}
