package com.example.couponSystem2.entities;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    FOOD(1),
    ELECTRICITY(2),
    RESTAURANT(3),
    VACATION(4);

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
