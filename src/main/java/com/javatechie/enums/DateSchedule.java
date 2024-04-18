package com.javatechie.enums;

public enum DateSchedule {
    ThuHai("1"),
    ThuBa("2"),
    ThuTu("3"),
    ThuNam("4"),
    ThuSau("5"),
    ThuBay("6"),
    ChuNhat("7");
    private final String value;

    DateSchedule(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
