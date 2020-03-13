package com.parser.model.dto.clothes;


import com.parser.model.common.LaModaSubCategories.clothes.ClothesSubCategory;
import com.parser.model.common.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClothesDto {

    private Long id;
    private ClothesSubCategory clothesSubCategory;
    private Sex sex;
    private BigDecimal price;
    private String description;
    private String clothesPath;
    private String imgPath;
}
