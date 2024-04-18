package com.javatechie.model.base;

import lombok.Data;

@Data
public class Status {
    public static String SUCCESS = "1";
    public static String FAIL = "0";
    public static String FAIL_RSA = "90";
    public static String INVALID_ROLE = "80";

    public static String CIF_PHONE_NOT_REGISTER = "106";

    private String code = "";
    private String message = "";
}
