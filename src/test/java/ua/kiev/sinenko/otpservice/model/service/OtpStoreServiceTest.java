package ua.kiev.sinenko.otpservice.model.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ua.kiev.sinenko.otpservice.model.entity.OtpEntity;
import ua.kiev.sinenko.otpservice.otp.properties.OtpGeneratorProperties;
import ua.kiev.sinenko.otpservice.otp.service.OtpGenerator;
import ua.kiev.sinenko.otpservice.otp.utils.DateUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
class OtpStoreServiceTest {
    private Logger logger = LoggerFactory.getLogger(OtpStoreServiceTest.class);

    @Autowired
    private OtpGenerator emailOtpGeneratorService;
    @Autowired
    private OtpStoreService otpStoreService;
    @Autowired
    private OtpGeneratorProperties otpGeneratorProperties;

    @Sql(value = "classpath:/ua/kiev/sinenko/OtpStore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    //@Test
    void shouldStoreOtpRecord() {
        String otp = emailOtpGeneratorService.generateOtp();
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtpValue(otp);
        otpEntity.setRecipient("andy.sinenko@gmail.com");
        otpEntity.setCreateDate(DateUtils.currentTime());
        otpEntity.setExpireDate(DateUtils.expireTime(otpGeneratorProperties.getTimeToLive()));

        OtpEntity stored = otpStoreService.save(otpEntity);

        logger.info("Stored OtpEntity: " + stored);
        assertNotNull(stored.getId());
    }

    @Sql(value = "classpath:/ua/kiev/sinenko/OtpStore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    //@Test
    void shouldUpdareOtpRecord() {
        String otp = emailOtpGeneratorService.generateOtp();
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtpValue(otp);
        otpEntity.setRecipient("andy.sinenko@gmail.com");
        otpEntity.setCreateDate(DateUtils.currentTime());
        otpEntity.setExpireDate(DateUtils.expireTime(otpGeneratorProperties.getTimeToLive()));

        OtpEntity stored = otpStoreService.save(otpEntity);
        UUID id = stored.getId();
        stored.setConfirmationCode("1111");
        stored.setIsSuccess(1);

        OtpEntity stored2 = otpStoreService.save(otpEntity);

        logger.info("Stored OtpEntity: " + stored2);

        assertNotNull(stored2.getIsSuccess());
        assertEquals(1, stored2.getIsSuccess());
    }
}