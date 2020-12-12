package ua.kiev.sinenko.otpservice.rest.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import ua.kiev.sinenko.otpservice.error.ExpiredChellangeException;
import ua.kiev.sinenko.otpservice.error.NoChellangeException;

@ControllerAdvice(annotations = RestController.class)
public class ErrorHandlerController {
    private Logger logger = LoggerFactory.getLogger(ErrorHandlerController.class);

    @ExceptionHandler(NoChellangeException.class)
    public ResponseEntity<ErrorResponse> orderNotFoundException(NoChellangeException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredChellangeException.class)
    public ResponseEntity<ErrorResponse> orderNotFoundException(ExpiredChellangeException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
