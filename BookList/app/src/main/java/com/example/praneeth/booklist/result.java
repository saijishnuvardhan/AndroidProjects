package com.example.praneeth.booklist;

import java.util.List;

public class result {

    private String kind;
    private String totalItems;
    private List<items> items;

    public result(String kind, String totalItems, List<com.example.praneeth.booklist.items> items) {
        this.kind = kind;
        this.totalItems = totalItems;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public List<com.example.praneeth.booklist.items> getItems() {
        return items;
    }
}
