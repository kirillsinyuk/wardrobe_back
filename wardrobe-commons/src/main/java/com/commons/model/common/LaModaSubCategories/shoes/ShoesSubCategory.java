package com.commons.model.common.LaModaSubCategories.shoes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public enum ShoesSubCategory {
    BALLET_SHOES(Collections.singletonList("Балетки")),
    SANDALS(Arrays.asList("Босоножки", "Сандалии")),
    ANKLE_BOOTS(Arrays.asList("Ботильоны", "Полусапоги")),
    SNEAKERS(Collections.singletonList("Кроссовки")),
    GUMSHOES(Collections.singletonList("Кеды")),
    BOOTS(Collections.singletonList("Ботинки")),
    HEEL_SHOES(Collections.singletonList("Туфли")),
    HIGH_BOOTS(Collections.singletonList("Сапоги")),
    CLOGS(Collections.singletonList("Сабо"));

    private List<String> description;

    ShoesSubCategory(List<String> descripiton) {
        this.description = descripiton;
    }

    public static ShoesSubCategory getSubCategoryByDescription(String desc) {
        return Arrays.stream(ShoesSubCategory.values())
                .filter(category -> category.description.contains(desc))
                .findFirst()
                .orElse(null);
    }

    public static ShoesSubCategory getSubCategoryByDescriptionList(List<String> desc) {
        return desc.stream()
                .map(ShoesSubCategory::getSubCategoryByDescription)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
