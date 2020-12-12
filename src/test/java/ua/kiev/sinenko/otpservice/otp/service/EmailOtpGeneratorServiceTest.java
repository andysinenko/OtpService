package ua.kiev.sinenko.otpservice.otp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.kiev.sinenko.otpservice.otp.properties.EmailOtpGeneratorProperties;
import ua.kiev.sinenko.otpservice.otp.properties.OtpGeneratorProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmailOtpGeneratorServiceTest {
    private Logger logger = LoggerFactory.getLogger(EmailOtpGeneratorServiceTest.class);


    @MockBean
    private EmailOtpGeneratorProperties emailGeneratorProperies;
    private EmailOtpGeneratorService emailOtpGeneratorService;
    private OtpGeneratorProperties otpGeneratorProperties = new OtpGeneratorProperties();

    //@Test
    void shouldGenerateOtpWhithSingeCharRepeated4Times() {
        when(emailGeneratorProperies.getEncoding()).thenReturn("UTF-8");
        when(emailGeneratorProperies.getOtpCharSequence()).thenReturn("1");
        when(emailGeneratorProperies.getOtpLength()).thenReturn(4);
        otpGeneratorProperties.setEmailGeneratorProperies(emailGeneratorProperies);
        emailOtpGeneratorService = new EmailOtpGeneratorService(otpGeneratorProperties);

        String otp = emailOtpGeneratorService.generateOtp();
        logger.info("otp = " + otp);
        assertEquals("1111", otp);
    }

    //@Test
    void shouldGenerateOtpWhithDifferent6Chars() {
        when(emailGeneratorProperies.getEncoding()).thenReturn("UTF-8");
        when(emailGeneratorProperies.getOtpCharSequence()).thenReturn("abcdefghijklmnopqrstuvwxyz0123456789");
        when(emailGeneratorProperies.getOtpLength()).thenReturn(6);
        otpGeneratorProperties.setEmailGeneratorProperies(emailGeneratorProperies);
        emailOtpGeneratorService = new EmailOtpGeneratorService(otpGeneratorProperties);

        String otp = emailOtpGeneratorService.generateOtp();
        logger.info("otp = " + otp);
        assertEquals(otp.length(), 6);
        assertTrue(otp.matches("\\d*\\w*"));
    }
}