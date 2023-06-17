package com.example.aruna.webflux.exception;

import com.example.aruna.webflux.dto.MultiplyRequest;

public class InvalidInputException extends RuntimeException {

    private static String MESSAGE  = "values must be greater than zero";
    public static final int errorCode = 100;
    private final MultiplyRequest input;

    public InvalidInputException(MultiplyRequest input) {
        super(MESSAGE);
        this.input = input;
    }

    public static String getMESSAGE() {
        return MESSAGE;
    }

    public MultiplyRequest getInput() {
        return input;
    }

}
