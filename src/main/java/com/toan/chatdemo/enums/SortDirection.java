package com.toan.chatdemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum SortDirection {
    ASC(0), DESC(1);

    private final int value;
    private static Map<Integer, SortDirection> map = new HashMap<>();

    private SortDirection(int value) {
        this.value = value;
    }

    static {
        for (SortDirection type : SortDirection.values()) {
            map.put(type.value, type);
        }
    }

    public static SortDirection valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
