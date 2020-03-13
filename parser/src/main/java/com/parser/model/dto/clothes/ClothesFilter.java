package com.parser.model.dto.clothes;

import com.parser.model.common.LaModaSubCategories.clothes.ClothesSubCategory;
import com.parser.model.dto.CommonFilter;
import lombok.Data;
import java.util.List;

@Data
public class ClothesFilter extends CommonFilter {
        private List<ClothesSubCategory> subCategory;
}
