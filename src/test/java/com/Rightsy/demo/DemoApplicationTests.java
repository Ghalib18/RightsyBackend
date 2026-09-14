package com.Rightsy.demo;

import com.Rightsy.demo.Dto.OtpType;
import com.Rightsy.demo.Service.OtpService;
import com.Rightsy.demo.Service.VerficationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private VerficationService verficationService;

    /*
    @Test
    void sendOtpTest() {
        verficationService.sendOtp(
                "trishankohli@gmail.com",
                "123456",
                OtpType.EmailVerfication
        );
    }
    */

	@Autowired
	private OtpService otpService;

	@Test
	void generateOtpTest() {

		String otp = otpService.generateOtpCode(
				"trishankohli@gmail.com",
				OtpType.EmailVerfication
		);

		System.out.println("Generated OTP: " + otp);
	}
}