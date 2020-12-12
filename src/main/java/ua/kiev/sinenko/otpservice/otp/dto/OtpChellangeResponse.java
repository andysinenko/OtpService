package ua.kiev.sinenko.otpservice.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpChellangeResponse implements Serializable {
    private static final long serialVersionUID = 1877697997163398327L;

    private String chellangeId;
    private String otp;
}
