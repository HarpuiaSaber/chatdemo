package com.toan.chatdemo.enums;

import java.util.HashMap;
import java.util.Map;

public enum ComparisonOperator {
    EQUAL(0),
    LESS_THAN(1),
    LESS_THAN_OR_EQUAL(2),
    GREATER_THAN(3),
    GREATER_THAN_OR_EQUAL(4),
    NOT_EQUAL(5),
    CONTAINS(6), //for strings
    STARTS_WITH(7), //for strings
    ENDS_WITH(8), //for strings
    IN(10); // for list item

    private final int value;
    private static Map<Integer, ComparisonOperator> map = new HashMap<>();

    private ComparisonOperator(int value) {
        this.value = value;
    }

    static {
        for (ComparisonOperator type : ComparisonOperator.values()) {
            map.put(type.value, type);
        }
    }

    public static ComparisonOperator valueOf(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }
}
