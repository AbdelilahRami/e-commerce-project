package com.shopping.application.service;

import com.shopping.application.dto.ProductCategoryDto;
import com.shopping.application.dto.ProductDto;
import com.shopping.application.exception.NoCategoryException;
import com.shopping.application.mapper.ProductCategoryMapper;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.repositorie.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;
    private final String NO_CATEGORY_FOUND_404 = "N0_CATEGORY_FOUND_404";

    public ProductCategoryService(ProductCategoryMapper productCategoryMapper, ProductCategoryRepository productCategoryRepository) {
        this.productCategoryMapper = productCategoryMapper;
        this.productCategoryRepository= productCategoryRepository;
    }

    public void create(ProductCategoryDto productCategoryDto) {

        ProductCategory productCategory = productCategoryMapper.productCategoryDtoToProductCategory(productCategoryDto);
        productCategoryRepository.save(productCategory);

    }

    public Optional<ProductCategory> getProductCategory(Long id) {
        return productCategoryRepository.findById(id);
    }

    public Boolean isExistedCategory(ProductDto productDto){
       return Optional.of(productDto.hasCategoryDto()).orElseThrow(()->new NoCategoryException(NO_CATEGORY_FOUND_404));
    }

}
