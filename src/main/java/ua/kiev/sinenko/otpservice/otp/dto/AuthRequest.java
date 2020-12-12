package ua.kiev.sinenko.otpservice.otp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {
    private static final long serialVersionUID = -8639761662120178352L;

    private String challangeId;
    private String otp;
}
