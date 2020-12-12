package ua.kiev.sinenko.otpservice.otp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResponse implements Serializable {
    private static final long serialVersionUID = -3366200444890954190L;

    private String authorization;
}
