package com.commons.services.converters;

import com.commons.model.LaModaClothes.Clothes;
import com.commons.model.common.Price;
import com.commons.model.dto.clothes.ClothesDto;

public class ClothesDtoConverter {

    private ClothesDtoConverter(){}

    public static Clothes convert(ClothesDto dto){
        Clothes clothes = new Clothes();
        clothes.setClothesSubCategory(dto.getClothesSubCategory());
        clothes.setPrice(new Price(dto.getPrice()));
        clothes.setSex(dto.getSex());
        clothes.setItemPath(dto.getClothesPath());
        clothes.setImgPath(dto.getImgPath());
        return clothes;
    }

    public static ClothesDto convertToDto(Clothes clothes){
        ClothesDto dto = new ClothesDto();
        dto.setId(clothes.getId());
        dto.setClothesSubCategory(clothes.getClothesSubCategory());
        dto.setSex(clothes.getSex());
        dto.setPrice(clothes.getPrice().getPrice());
        dto.setClothesPath(clothes.getItemPath());
        dto.setDescription(clothes.getDescription());
        dto.setImgPath(clothes.getImgPath());
        return dto;
    }
}
