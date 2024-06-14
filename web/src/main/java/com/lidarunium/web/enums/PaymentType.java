package com.lidarunium.web.enums;

public enum PaymentType {
    CASH("Наличные"),
    NONCASH("Безналичные");

    private final String name;

    private PaymentType(String name) {
        this.name = name;
    }

}
