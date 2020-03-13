package com.parser.model.common;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public enum Category {
    SHOES("Обувь"),
    CLOTHES("Одежда"),
    ACCS("Аксессуары");

    private String description;

    public static Category getCategoryByDescription(String desc) {
        return Arrays.stream(Category.values())
                .filter(category -> category.description.equals(desc))
                .findFirst()
                .orElse(null);
    }

    public static Category getCategoryByDescriptionList(List<String> desc) {
        return desc.stream()
                .map(Category::getCategoryByDescription)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
