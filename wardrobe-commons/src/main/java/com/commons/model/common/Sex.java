package com.commons.model.common;

import java.util.Arrays;
import java.util.List;

public enum Sex {
    WOMAN("Женщинам"),
    MAN("Мужчинам");

    private String decsription;

    Sex(String decsription) {
        this.decsription = decsription;
    }

    public static Sex getCategoryByDescription(String desc) {
        return Arrays.stream(Sex.values())
                .filter(category -> category.decsription.equals(desc))
                .findFirst()
                .orElse(null);
    }

    public static Sex getCategoryByDescriptionList(List<String> desc) {
        return desc.stream()
                .map(Sex::getCategoryByDescription)
                .filter(cat -> cat != null)
                .findFirst()
                .orElse(null);
    }
}
