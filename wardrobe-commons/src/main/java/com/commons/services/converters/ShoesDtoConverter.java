package com.commons.services.converters;

import com.commons.model.LaModaClothes.Shoes;
import com.commons.model.common.Price;
import com.commons.model.dto.shoes.ShoesDto;

public class ShoesDtoConverter {

    private ShoesDtoConverter(){}

    public static Shoes convert(ShoesDto dto){
        Shoes shoes = new Shoes();
        shoes.setShoesSubCategory(dto.getShoesSubCategory());
        shoes.setPrice(new Price(dto.getPrice()));
        shoes.setItemPath(dto.getClothesPath());
        shoes.setImgPath(dto.getImgPath());
        return shoes;
    }

    public static ShoesDto convertToDto(Shoes shoes){
        ShoesDto dto = new ShoesDto();
        dto.setId(shoes.getId());
        dto.setShoesSubCategory(shoes.getShoesSubCategory());
        dto.setSex(shoes.getSex());
        dto.setPrice(shoes.getPrice().getPrice());
        dto.setDescription(shoes.getDescription());
        dto.setClothesPath(shoes.getItemPath());
        dto.setImgPath(shoes.getImgPath());
        return dto;
    }
}
