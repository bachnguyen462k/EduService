package com.javatechie.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(510, "account.not.found", HttpStatus.NOT_EXTENDED)

    ;
    private int code;

    private String message;

    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
