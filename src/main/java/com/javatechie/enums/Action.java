package com.javatechie.enums;

public enum Action {
    ACTIVE("Active"),// khởi tạo
    DELETE("Delete"), //xoa
    LOCK("Lock"), //xoa
    CANCEL("Cancel");// hủy
    private final String value;

    Action(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
