package com.example.couponSystem2.entities;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    FOOD(0),
    ELECTRICITY(1),
    RESTAURANT(2),
    VACATION(3);

    private int value;
    private static Map map = new HashMap<>();

    Category(int value) {
        this.value = value;
    }

    static {
        for (Category category : Category.values()) {
            map.put(category.value, category);
        }
    }

    public static Category valueOf(int categoryType) {
        return (Category) map.get(categoryType);
    }

    public int getValue() {
        return value;
    }
}
