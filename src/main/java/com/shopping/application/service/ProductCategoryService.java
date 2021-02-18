package com.shopping.application.service;

import com.shopping.application.dto.ProductCategoryDto;
import com.shopping.application.mapper.ProductCategoryMapper;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.repositorie.ProductCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryMapper productCategoryMapper, ProductCategoryRepository productCategoryRepository) {
        this.productCategoryMapper = productCategoryMapper;
        this.productCategoryRepository= productCategoryRepository;
    }

    public void create(ProductCategoryDto productCategoryDto) {

        ProductCategory productCategory = productCategoryMapper.productCategoryDtoToProductCategory(productCategoryDto);
        productCategoryRepository.save(productCategory);

    }
}
