package com.parser.model.dto.shoes;

import com.parser.model.common.LaModaSubCategories.shoes.ShoesSubCategory;
import com.parser.model.dto.CommonFilter;
import lombok.Data;

import java.util.List;

@Data
public class ShoesFilter extends CommonFilter {
        private List<ShoesSubCategory> subCategory;
}
