package com.shopping.application.repositorie;

import com.shopping.application.models.Product;
import com.shopping.application.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findByBrandName(String brandName);

    Optional<Product> findById(UUID id);

    List<Product> findAllByProductCategory(ProductCategory productCategory);
}
