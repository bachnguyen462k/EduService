package com.javatechie.exception;

import com.javatechie.enums.BaseErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class BusinessException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    private final BaseErrorCode errorCode;

    private final String detailMessage;

    private Object[] args;

    public BusinessException(BaseErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        this.detailMessage = "";
    }

    public BusinessException(BaseErrorCode errorCode, Object... params) {
        super();
        this.errorCode = errorCode;
        this.args = params;
        this.detailMessage = "";
    }

//	public BusinessException(String message, Throwable cause, BaseErrorCode errorCode) {
//		super(message, cause);
//		this.errorCode = errorCode;
//		this.detailMessage = message;
//	}

    public BusinessException(String message, BaseErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.detailMessage = message;
    }

    public BusinessException(Throwable cause, BaseErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.detailMessage = "";
    }

    public Object[] getArgs() {
        return args;
    }

    public int getCode() {
        return this.errorCode.getCode();
    }

    public String getMessage() {
//        if (StringUtils.isBlank(this.errorCode.getMessage())) {
            return "loi mess";
//        }
//        return this.errorCode.getMessage();
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public HttpStatus getHttpStatus() {
        return this.errorCode.getHttpStatus();
    }
}