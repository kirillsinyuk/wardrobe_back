package com.parser.model.common.LaModaSubCategories.clothes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public enum ClothesSubCategory {

    SHIRT(Arrays.asList("Футболки", "Рубашки", "Майки")),
    BLOUSE(Collections.singletonList("Блузы")),
    BODY(Collections.singletonList("Боди")),
    TOP(Collections.singletonList("Топы")),
    JEANS(Collections.singletonList("Джинсы")),
    SKIRT(Collections.singletonList("Юбки")),
    PANTS(Collections.singletonList("Брюки")),
    SHORT(Collections.singletonList("Шорты")),
    PULLOVER(Collections.singletonList("Свитеры")),
    LEATHER_JACKET(Collections.singletonList("Кожаные куртки")),
    COAT(Collections.singletonList("Пальто"));

    private List<String> description;

    ClothesSubCategory(List<String> descripiton) {
        this.description = descripiton;
    }

    public static ClothesSubCategory getSubCategoryByDescription(String desc) {
        return Arrays.stream(ClothesSubCategory.values())
                .filter(category -> category.description.contains(desc))
                .findFirst()
                .orElse(null);
    }

    public static ClothesSubCategory getSubCategoryByDescriptionList(List<String> desc) {
        return desc.stream()
                .map(ClothesSubCategory::getSubCategoryByDescription)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
