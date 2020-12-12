package ua.kiev.sinenko.otpservice.otp.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
public class EmailOtpGeneratorProperties {
    private Integer otpLength;
    private String otpCharSequence;
    private String encoding;
}
