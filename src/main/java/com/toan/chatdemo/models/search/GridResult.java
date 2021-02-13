package com.toan.chatdemo.models.search;

import java.io.Serializable;
import java.util.List;

public class GridResult<T> implements Serializable {
    private long totalItems;
    private List<T> items;

    public GridResult() {
    }

    public GridResult(long totalItems, List<T> items) {
        this.totalItems = totalItems;
        this.items = items;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
