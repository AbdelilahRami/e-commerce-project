package com.shopping.application.repositorie;

import com.shopping.application.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> getProductCategoryByCategoryName(String categoryName);

    Optional<ProductCategory> getById(Long id);
}
