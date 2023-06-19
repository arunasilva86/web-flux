package com.example.aruna.webflux.dto;

import lombok.Data;

@Data
public class InvalidParamErrorResponse {
    private int errorCode;
    private String message;
    private Object body;
}
