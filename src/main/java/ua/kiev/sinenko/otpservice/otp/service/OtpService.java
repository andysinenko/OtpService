package ua.kiev.sinenko.otpservice.otp.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.kiev.sinenko.otpservice.error.ExpiredChellangeException;
import ua.kiev.sinenko.otpservice.error.NoChellangeException;
import ua.kiev.sinenko.otpservice.model.entity.AccessUserEntity;
import ua.kiev.sinenko.otpservice.model.entity.OtpEntity;
import ua.kiev.sinenko.otpservice.model.entity.SessionEntity;
import ua.kiev.sinenko.otpservice.model.service.OtpStoreService;
import ua.kiev.sinenko.otpservice.model.service.SessionService;
import ua.kiev.sinenko.otpservice.otp.dto.OtpChellangeResponse;
import ua.kiev.sinenko.otpservice.otp.dto.OtpVerificationResponse;
import ua.kiev.sinenko.otpservice.otp.properties.OtpGeneratorProperties;
import ua.kiev.sinenko.otpservice.otp.sender.SenderService;
import ua.kiev.sinenko.otpservice.otp.utils.DateUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
public class OtpService {
    private Logger logger = LoggerFactory.getLogger(OtpService.class);

    private OtpGenerator emailOtpGeneratorService;
    private OtpStoreService otpStoreService;
    private OtpGeneratorProperties otpGeneratorProperties;
    private SenderService emailSenderService;
    private SessionService sessionService;

    public OtpService(OtpGenerator emailOtpGeneratorService, OtpStoreService otpStoreService,
                      OtpGeneratorProperties otpGeneratorProperties, SenderService emailSenderService,
                      SessionService sessionService) {
        this.emailOtpGeneratorService = emailOtpGeneratorService;
        this.otpStoreService = otpStoreService;
        this.otpGeneratorProperties = otpGeneratorProperties;
        this.emailSenderService = emailSenderService;
        this.sessionService = sessionService;
    }

    public OtpChellangeResponse generateChellange(String receipient) {
        String otp = emailOtpGeneratorService.generateOtp();
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtpValue(otp);
        otpEntity.setCreateDate(DateUtils.currentTime());
        otpEntity.setExpireDate(DateUtils.expireTime(otpGeneratorProperties.getTimeToLive()));

        OtpEntity stored = otpStoreService.save(otpEntity);

        OtpChellangeResponse response = new OtpChellangeResponse(stored.getId().toString(), stored.getOtpValue());
        emailSenderService.sendOtp(receipient, response.getOtp());

        return response;
    }

    public OtpVerificationResponse verifyOtp(String otpValue, String chellangeId) throws NoChellangeException, ExpiredChellangeException {
        OtpEntity otpEntity = otpStoreService.findById(UUID.fromString(chellangeId));

        if (otpEntity.getConfirmationCode() != null) {
            throw new ExpiredChellangeException("Otp was used");
        }

        otpEntity.setConfirmationCode(otpValue);
        if (otpEntity.getExpireDate().before(DateUtils.currentTime())) {
            logger.error("Otp time expired");
            otpEntity.setIsSuccess(0);
            otpStoreService.save(otpEntity);
            throw new ExpiredChellangeException("Otp time expired");
        }
        otpEntity.setIsSuccess(1);
        otpStoreService.save(otpEntity);

        SessionEntity sessionEntity = generateSessionEntity(new AccessUserEntity());
        sessionEntity = sessionService.save(sessionEntity);
        String base64TokengenerateSessionToken = generateSessionToken(sessionEntity);

        OtpVerificationResponse response = new OtpVerificationResponse(sessionEntity.getId().toString(), base64TokengenerateSessionToken);

        return response;
    }

    private String generateSessionToken(SessionEntity sessionEntity) {
        String tokenString = sessionEntity.getAccessUserEntity().getUserName() + ":" + sessionEntity.getId() +
                ":" +sessionEntity.getExpireTime();

        return Base64.encodeBase64String(tokenString.getBytes());
    }

    private SessionEntity generateSessionEntity(AccessUserEntity accessUserEntity) {
        SessionEntity entity = new SessionEntity();
        entity.setStartTime(DateUtils.currentTime());
        entity.setExpireTime(DateUtils.expireTime(30000L));
        entity.setAccessUserEntity(accessUserEntity);

        return entity;
    }
}
