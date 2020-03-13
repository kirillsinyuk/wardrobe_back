package com.commons.model.dto.shoes;

import com.commons.model.common.LaModaSubCategories.shoes.ShoesSubCategory;
import com.commons.model.dto.CommonFilter;
import lombok.Data;

import java.util.List;

@Data
public class ShoesFilter extends CommonFilter {
        private List<ShoesSubCategory> subCategory;
}
