package com.api.repository;

import com.commons.model.LaModaClothes.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long>, JpaSpecificationExecutor<Shoes> {
}
