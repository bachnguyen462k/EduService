package com.javatechie.enums;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    public int getCode();

    public String getMessage();

    HttpStatus getHttpStatus();
}

