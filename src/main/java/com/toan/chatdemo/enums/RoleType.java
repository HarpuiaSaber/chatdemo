package com.toan.chatdemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleType {
    USER(0), ADMIN(10);

    private final int value;
    private static Map<Integer, RoleType> map = new HashMap<>();

    private RoleType(int value) {
        this.value = value;
    }

    static {
        for (RoleType type : RoleType.values()) {
            map.put(type.value, type);
        }
    }

    public static RoleType valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
