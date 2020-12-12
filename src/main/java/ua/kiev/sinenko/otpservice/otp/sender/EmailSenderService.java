package ua.kiev.sinenko.otpservice.otp.sender;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ua.kiev.sinenko.otpservice.otp.properties.OtpGeneratorProperties;

@Component
public class EmailSenderService implements SenderService {
    private JavaMailSender emailSender;
    private OtpGeneratorProperties otpGeneratorProperties;

    public EmailSenderService(JavaMailSender emailSender, OtpGeneratorProperties otpGeneratorProperties) {
        this.emailSender = emailSender;
        this.otpGeneratorProperties = otpGeneratorProperties;
    }

    public void sendOtp(String recipient, String otp) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String message = String.format(otpGeneratorProperties.getMessageTemplate(), otp);
        //simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(otpGeneratorProperties.getSubjectMessage());
        simpleMailMessage.setText(message);

        emailSender.send(simpleMailMessage);
    }
}
