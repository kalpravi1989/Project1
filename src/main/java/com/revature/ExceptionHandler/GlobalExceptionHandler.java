package com.revature.ExceptionHandler;


import com.revature.Aspects.SessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // Or choose an appropriate status
    @ResponseBody
    public ErrorResponse handleSessionNotFoundException(SessionNotFoundException ex) {
        return new ErrorResponse("SESSION_ERROR", ex.getMessage());
    }

    // Additional exception handlers can be added here

    // Custom error response class
    public static class ErrorResponse {
        private String errorCode;
        private String errorMessage;

        public ErrorResponse(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
