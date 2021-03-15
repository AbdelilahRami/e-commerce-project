package com.shopping.application.repositorie;

import com.shopping.application.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findBrandByBrandName(String brandName);
}
