package com.api.repository;

import com.commons.model.Item;
import com.commons.model.common.LaModaSubCategories.clothes.ClothesSubCategory;
import com.commons.model.common.LaModaSubCategories.shoes.ShoesSubCategory;
import com.commons.model.common.Sex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class SpecificationBuilder<T extends Item> {

    public Specification<T> stringSpec(String attribute, String value) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(value) ? null : criteriaBuilder.like(root.get(attribute), "%" + value + "%");
    }

    public Specification<T> stringListSpec(String attribute, List<String> value) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(value) ? null : criteriaBuilder.and(root.get(attribute).in(value));
    }

    public Specification<T> longSpec(String attribute, Long value) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(value) ? null : criteriaBuilder.equal(root.get(attribute), value);
    }

    public Specification<T> sexSpec(String attribute, Sex sex) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(sex) ? null : criteriaBuilder.equal(root.get(attribute), sex);
    }

    public Specification<T> minPriceSpec(BigDecimal price) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(price) ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public Specification<T> maxPriceSpec(BigDecimal price) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(price) ? null : criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public Specification<T> shoesSubType(List<ShoesSubCategory> subType) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(subType) ? null : criteriaBuilder.and(root.get("shoesSubCategory").in(subType));
    }

    public Specification<T> clothesSubType(Collection<ClothesSubCategory> subType) {
        return (root, query, criteriaBuilder) ->
                Objects.isNull(subType) ? null : criteriaBuilder.and(root.get("clothesSubCategory").in(subType));
    }
}
