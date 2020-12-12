package ua.kiev.sinenko.otpservice.rest.errorhandling;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    private String errorMessage;
    private String additionalDatails;

    public ErrorResponse(String errorMessage, String additionalDatails) {
        this.errorMessage = errorMessage;
        this.additionalDatails = additionalDatails;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAdditionalDatails() {
        return additionalDatails;
    }

    public void setAdditionalDatails(String additionalDatails) {
        this.additionalDatails = additionalDatails;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorMessage='" + errorMessage + '\'' +
                ", additionalDatails='" + additionalDatails + '\'' +
                '}';
    }
}
