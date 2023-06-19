package com.example.aruna.webflux.exception;

public class InvalidInputException extends RuntimeException {

    private final String message = "values must be greater than zero";
    public static final int errorCode = 100;
    private final Object input;

    public InvalidInputException(Object input, String message) {
        super(message);
        this.input = input;
    }

    public String getMessage() {
        return message;
    }

    public Object getInput() {
        return input;
    }



}
