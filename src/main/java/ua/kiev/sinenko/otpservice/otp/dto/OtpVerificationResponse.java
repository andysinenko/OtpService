package ua.kiev.sinenko.otpservice.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class OtpVerificationResponse implements Serializable {
    private static final long serialVersionUID = 7108712512529525089L;
    private String sessionId;
    private String authorization;

    public OtpVerificationResponse(String sessionId, String authorization) {
        this.sessionId = sessionId;
        this.authorization = authorization;
    }
}
