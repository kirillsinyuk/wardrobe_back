package com.parser.repository;

import com.parser.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WardrobeRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
}
