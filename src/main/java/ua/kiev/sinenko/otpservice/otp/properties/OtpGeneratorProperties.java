package ua.kiev.sinenko.otpservice.otp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "otp")
public class OtpGeneratorProperties {
    EmailOtpGeneratorProperties emailGeneratorProperies = new EmailOtpGeneratorProperties();
    Long timeToLive;
    String messageTemplate;
    String senderAddress;
    String subjectMessage;
}
