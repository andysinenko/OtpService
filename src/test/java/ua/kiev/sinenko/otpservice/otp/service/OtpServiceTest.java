package ua.kiev.sinenko.otpservice.otp.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.kiev.sinenko.otpservice.error.ExpiredChellangeException;
import ua.kiev.sinenko.otpservice.error.NoChellangeException;
import ua.kiev.sinenko.otpservice.model.entity.OtpEntity;
import ua.kiev.sinenko.otpservice.model.entity.SessionEntity;
import ua.kiev.sinenko.otpservice.model.service.OtpStoreService;
import ua.kiev.sinenko.otpservice.model.service.SessionService;
import ua.kiev.sinenko.otpservice.otp.dto.OtpChellangeResponse;
import ua.kiev.sinenko.otpservice.otp.properties.OtpGeneratorProperties;
import ua.kiev.sinenko.otpservice.otp.sender.EmailSenderService;
import ua.kiev.sinenko.otpservice.otp.utils.DateUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class OtpServiceTest {
    private Logger logger = LoggerFactory.getLogger(OtpServiceTest.class);
    private String testOtpValue = "1111";

    @MockBean
    private OtpGenerator emailOtpGeneratorService;
    @MockBean
    private OtpStoreService otpStoreService;
    @MockBean
    private OtpGeneratorProperties otpGeneratorProperties;
    @MockBean
    private EmailSenderService emailSenderService;
    @MockBean
    private SessionService sessionService;

    private OtpService otpService;

    //@Test
    void generateChellange() {
        OtpEntity entity = createOtpEntity();
        SessionEntity sessionEntity = createSessionEntity();

        when(emailOtpGeneratorService.generateOtp()).thenReturn(testOtpValue);
        when(otpStoreService.save(any())).thenReturn(entity);
        when(sessionService.save(any())).thenReturn(sessionEntity);
        when(otpGeneratorProperties.getTimeToLive()).thenReturn(600L);

        otpService = new OtpService(emailOtpGeneratorService, otpStoreService, otpGeneratorProperties, emailSenderService, sessionService);
        OtpChellangeResponse response = otpService.generateChellange("andy.sinenko@gmail.com");

        logger.info("OtpChellangeResponse: " + response);

        assertNotNull(response.getChellangeId());
        assertNotNull(response.getOtp());
        assertEquals(testOtpValue, response.getOtp());
    }

    //@Test
    void verifyOtp() throws NoChellangeException, ExpiredChellangeException {
        OtpEntity entity = createOtpEntityExpired();
        SessionEntity sessionEntity = createSessionEntity();

        when(emailOtpGeneratorService.generateOtp()).thenReturn(testOtpValue);
        when(otpStoreService.save(any())).thenReturn(entity);
        when(otpStoreService.findById(any())).thenReturn(entity);
        when(otpGeneratorProperties.getTimeToLive()).thenReturn(600L);

        otpService = new OtpService(emailOtpGeneratorService, otpStoreService, otpGeneratorProperties, emailSenderService, sessionService);

        Exception exception = assertThrows(ExpiredChellangeException.class,
                () -> otpService.verifyOtp(testOtpValue, entity.getId().toString()),
                "Should throw Otp time expired");
        String expectedMessage = "Otp time expired";
        String actualMessage = exception.getMessage();

        logger.info("actualMessage", actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private OtpEntity createOtpEntity() {
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setId(UUID.randomUUID());
        otpEntity.setOtpValue(testOtpValue);
        otpEntity.setCreateDate(DateUtils.currentTime());
        otpEntity.setExpireDate(DateUtils.expireTime(otpGeneratorProperties.getTimeToLive()));

        return otpEntity;
    }

    private OtpEntity createOtpEntityExpired() {
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setId(UUID.randomUUID());
        otpEntity.setOtpValue(testOtpValue);
        otpEntity.setCreateDate(DateUtils.currentTime());
        otpEntity.setExpireDate(DateUtils.currentTime());

        return otpEntity;
    }

    private SessionEntity createSessionEntity() {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId(UUID.randomUUID());
        sessionEntity.setStartTime(DateUtils.currentTime());
        sessionEntity.setExpireTime(DateUtils.expireTime(otpGeneratorProperties.getTimeToLive()));

        return sessionEntity;
    }


}