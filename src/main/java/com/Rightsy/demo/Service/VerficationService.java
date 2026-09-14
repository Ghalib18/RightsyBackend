package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.OtpType;

public interface VerficationService {

    void sendOtp(String email, String code, OtpType otpType);
}
