package com.api.services.api;

import com.api.repository.ShoesRepository;
import com.api.repository.SpecificationBuilder;
import com.commons.model.LaModaClothes.Shoes;
import com.commons.model.dto.shoes.ShoesDto;
import com.commons.model.dto.shoes.ShoesFilter;
import com.commons.services.converters.ShoesDtoConverter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ShoesService {

    @Autowired
    ShoesRepository shoesRepository;

    public Page<ShoesDto> findFiltered(@NonNull ShoesFilter filter, Pageable pageable) {
        SpecificationBuilder<Shoes> sp = new SpecificationBuilder<>();

        Specification<Shoes> shoesSpecification =  Specification.where(
                    sp.stringListSpec("brand", filter.getBrand())
                    .and(sp.sexSpec("sex", filter.getSex()))
                    .and(sp.longSpec("id", filter.getId()))
                    .and(sp.maxPriceSpec(filter.getMax()))
                    .and(sp.minPriceSpec(filter.getMin()))
                    .and(sp.shoesSubType(filter.getSubCategory()))
        );

        return shoesRepository.findAll(shoesSpecification, pageable)
                .map(ShoesDtoConverter::convertToDto);
    }

}
