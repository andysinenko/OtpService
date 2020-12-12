package ua.kiev.sinenko.otpservice.otp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;


public interface OtpGenerator {
    String generateOtp();
}
