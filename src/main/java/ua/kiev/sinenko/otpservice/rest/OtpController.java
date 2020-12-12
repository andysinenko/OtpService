package ua.kiev.sinenko.otpservice.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kiev.sinenko.otpservice.error.ExpiredChellangeException;
import ua.kiev.sinenko.otpservice.error.NoChellangeException;
import ua.kiev.sinenko.otpservice.otp.dto.*;
import ua.kiev.sinenko.otpservice.otp.service.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {
    private Logger logger = LoggerFactory.getLogger(OtpController.class);

    @Autowired
    private OtpService otpService;

    @ResponseBody
    @GetMapping("/{email}")
    public ResponseEntity<OtpChellangeResponse> getOtp(@PathVariable(value = "email") String email) {
        logger.debug("got parameter email: " + email);

        OtpChellangeResponse response = otpService.generateChellange(email);
        logger.debug("OtpChellangeResponse: " + response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<OtpVerificationResponse> getAuthorization(@RequestBody OtpVerificationRequest otpVerificationRequest) throws NoChellangeException, ExpiredChellangeException {
        OtpVerificationResponse response = otpService.verifyOtp(otpVerificationRequest.getOtp(), otpVerificationRequest.getChellangeId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
