package com.toan.chatdemo.models.search;

import com.toan.chatdemo.enums.SortDirection;

import java.io.Serializable;
import java.util.List;

public class GridParam implements Serializable {
    private String propertySort;
    private SortDirection sortDirection;
    private List<FilterItem> filterItems;
    private Integer start;
    private Integer length;

    public GridParam() {
    }

    public GridParam(String propertySort, SortDirection sortDirection, List<FilterItem> filterItems) {
        this.propertySort = propertySort;
        this.sortDirection = sortDirection;
        this.filterItems = filterItems;
    }

    public String getPropertySort() {
        return propertySort;
    }

    public void setPropertySort(String propertySort) {
        this.propertySort = propertySort;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public List<FilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<FilterItem> filterItems) {
        this.filterItems = filterItems;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
