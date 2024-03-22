package com.khadbhandarserver.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khadbhandarserver.inventory.entity.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	List<ProductCategory> findByCategoryName(String categoryName);
}
