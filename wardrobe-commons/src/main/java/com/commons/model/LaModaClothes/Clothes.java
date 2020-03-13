package com.commons.model.LaModaClothes;

import com.commons.model.Item;
import com.commons.model.common.LaModaSubCategories.clothes.ClothesSubCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name="clothes")
@Getter
@Setter
public class Clothes extends Item {
    @Column(name="sub_category")
    @Enumerated(EnumType.STRING)
    private ClothesSubCategory clothesSubCategory;
}
