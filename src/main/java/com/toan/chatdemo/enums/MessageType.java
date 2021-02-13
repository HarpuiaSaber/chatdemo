package com.toan.chatdemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum MessageType {
    JOIN(0), CHAT(1), LEAVE(2);

    private final int value;
    private static Map<Integer, MessageType> map = new HashMap<>();

    private MessageType(int value) {
        this.value = value;
    }

    static {
        for (MessageType type : MessageType.values()) {
            map.put(type.value, type);
        }
    }

    public static MessageType valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
