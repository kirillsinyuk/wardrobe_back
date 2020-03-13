package com.commons.model.LaModaClothes;

import com.commons.model.Item;
import com.commons.model.common.LaModaSubCategories.shoes.ShoesSubCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name="shoes")
@Getter
@Setter
public class Shoes extends Item {

    @Column(name="sub_category")
    @Enumerated(EnumType.STRING)
    private ShoesSubCategory shoesSubCategory;
}
