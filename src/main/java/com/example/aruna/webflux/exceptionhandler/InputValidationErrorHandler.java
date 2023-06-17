package com.example.aruna.webflux.exceptionhandler;

import com.example.aruna.webflux.dto.InvalidParamErrorResponse;
import com.example.aruna.webflux.exception.InvalidInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // important to have this
public class InputValidationErrorHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<InvalidParamErrorResponse> handleInputValidationErrors (InvalidInputException exception) {

        InvalidParamErrorResponse response = new InvalidParamErrorResponse();
        response.setErrorCode(InvalidInputException.errorCode);
        response.setMessage(exception.getMessage());
        response.setMultiplyRequest(exception.getInput());

        return ResponseEntity.badRequest().body(response);
    }
}
