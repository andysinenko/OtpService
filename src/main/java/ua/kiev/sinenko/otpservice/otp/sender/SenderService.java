package ua.kiev.sinenko.otpservice.otp.sender;

public interface SenderService {
    void sendOtp(String recipient, String otp);
}
