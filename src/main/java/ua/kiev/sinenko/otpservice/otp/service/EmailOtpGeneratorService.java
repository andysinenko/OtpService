package ua.kiev.sinenko.otpservice.otp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.sinenko.otpservice.otp.properties.OtpGeneratorProperties;

import java.nio.charset.Charset;
import java.security.SecureRandom;


@Service
public class EmailOtpGeneratorService implements OtpGenerator {
    private Logger logger = LoggerFactory.getLogger(EmailOtpGeneratorService.class);

    private OtpGeneratorProperties otpGeneratorProperties;

    public EmailOtpGeneratorService(OtpGeneratorProperties otpGeneratorProperties) {
        this.otpGeneratorProperties = otpGeneratorProperties;
    }

    @Override
    public String generateOtp() {
        String symbols = otpGeneratorProperties.getEmailGeneratorProperies().getOtpCharSequence();
        Integer length = otpGeneratorProperties.getEmailGeneratorProperies().getOtpLength();
        Charset charset = Charset.forName(otpGeneratorProperties.getEmailGeneratorProperies().getEncoding());

        if (length == 0 || symbols == null || symbols.length() == 0) {
            return "";
        }
        byte[] symbolsArray = symbols.getBytes(charset);
        int totalBytesRange = symbolsArray.length;
        byte[] passwordArray = new byte[length];
        for (int i = 0; i < length; i++)
        {
            SecureRandom secRan = new SecureRandom() ;
            passwordArray[i] = symbolsArray[secRan.nextInt(totalBytesRange)];
        }
        return new String(passwordArray, charset);
    }
}
