package com.javatechie.enums;

public enum Role {
    ADMIN("Admin"),
    TEACHER("Teacher"),
    STUDENT("Student");
    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
