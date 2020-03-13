package com.commons.model.dto.clothes;

import com.commons.model.common.LaModaSubCategories.clothes.ClothesSubCategory;
import com.commons.model.dto.CommonFilter;
import lombok.Data;

import java.util.List;

@Data
public class ClothesFilter extends CommonFilter {
        private List<ClothesSubCategory> subCategory;
}
