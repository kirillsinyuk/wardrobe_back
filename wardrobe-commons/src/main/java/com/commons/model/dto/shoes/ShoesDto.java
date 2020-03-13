package com.commons.model.dto.shoes;


import com.commons.model.common.LaModaSubCategories.shoes.ShoesSubCategory;
import com.commons.model.common.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoesDto {

    private Long id;
    private ShoesSubCategory shoesSubCategory;
    private Sex sex;
    private BigDecimal price;
    private String description;
    private String clothesPath;
    private String imgPath;
}
