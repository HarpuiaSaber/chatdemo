package com.toan.chatdemo.models.search;


import com.toan.chatdemo.enums.ComparisonOperator;

import java.io.Serializable;

public class FilterItem implements Serializable {
    private String propertyName;
    private Object value;
    private ComparisonOperator comparison;

    public FilterItem() {
    }

    public FilterItem(String propertyName, Object value, ComparisonOperator comparison) {
        this.propertyName = propertyName;
        this.value = value;
        this.comparison = comparison;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ComparisonOperator getComparison() {
        return comparison;
    }

    public void setComparison(ComparisonOperator comparison) {
        this.comparison = comparison;
    }
}
