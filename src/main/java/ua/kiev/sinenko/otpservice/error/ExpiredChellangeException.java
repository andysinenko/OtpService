package ua.kiev.sinenko.otpservice.error;

public class ExpiredChellangeException extends Exception {

    public ExpiredChellangeException(String message) {
        super(message);
    }
}
