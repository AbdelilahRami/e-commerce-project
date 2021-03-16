package com.shopping.application.controller;

import com.shopping.application.dto.ProductCategoryDto;
import com.shopping.application.exception.ProductCategoryNotFoundException;
import com.shopping.application.mapper.ProductCategoryMapper;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryService = productCategoryService;
        this.productCategoryMapper = productCategoryMapper;
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDto> createCategory(@RequestBody ProductCategoryDto productCategoryDto){
        ProductCategory returnedCategory = productCategoryService.create(productCategoryDto);
        ProductCategoryDto categoryDTO= productCategoryMapper.productCategoryToProductCategoryDto(returnedCategory);
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getProductCategory(@PathVariable Long id ) throws ProductCategoryNotFoundException{
        ProductCategoryDto productCategoryDto = productCategoryService.getProductCategory(id)
                                            .map(productCategoryMapper::productCategoryToProductCategoryDto)
                                            .orElseThrow(ProductCategoryNotFoundException::new);
        return ResponseEntity.ok().body(productCategoryDto);
    }
}
