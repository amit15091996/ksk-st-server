package com.khadbhandarserver.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.ProductCategory;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
