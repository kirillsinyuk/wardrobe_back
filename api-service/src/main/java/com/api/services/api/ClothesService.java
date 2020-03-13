package com.api.services.api;

import com.api.repository.ClothesRepository;
import com.api.repository.SpecificationBuilder;
import com.commons.model.LaModaClothes.Clothes;
import com.commons.model.dto.clothes.ClothesDto;
import com.commons.model.dto.clothes.ClothesFilter;
import com.commons.services.converters.ClothesDtoConverter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class ClothesService {

    @Autowired
    ClothesRepository clothesRepository;

    public Page<ClothesDto> findFiltered(@NonNull ClothesFilter filter, Pageable pageable) {
        SpecificationBuilder<Clothes> sp = new SpecificationBuilder<>();

        Specification<Clothes> clothesSpecification =  Specification.where(
                sp.stringListSpec("brand", filter.getBrand())
                        .and(sp.sexSpec("sex", filter.getSex()))
                        .and(sp.longSpec("id", filter.getId()))
                        .and(sp.maxPriceSpec(filter.getMax()))
                        .and(sp.minPriceSpec(filter.getMin()))
                        .and(sp.clothesSubType(filter.getSubCategory()))
        );

        return clothesRepository.findAll(clothesSpecification, pageable)
                .map(ClothesDtoConverter::convertToDto);
    }
}
