package ua.kiev.sinenko.otpservice.otp.sender;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSenderServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    //@Test
    void sendOtp() {
        String recipient = "andy.sinenko@gmail.com";
        String message = "1111";
        emailSenderService.sendOtp(recipient, message);
    }
}