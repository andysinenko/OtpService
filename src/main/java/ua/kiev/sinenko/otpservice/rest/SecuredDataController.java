package ua.kiev.sinenko.otpservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.sinenko.otpservice.otp.dto.OtpChellangeResponse;

@RestController
@RequestMapping("/data")
public class SecuredDataController {
    @ResponseBody
    @RequestMapping("/message")
    public ResponseEntity<String> getOtp(@PathVariable(value = "email") String email) {
        OtpChellangeResponse response = new OtpChellangeResponse();
        return new ResponseEntity<String>("Hello from secured area", HttpStatus.OK);
    }
}
