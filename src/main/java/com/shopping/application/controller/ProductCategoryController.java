package com.shopping.application.controller;

import com.shopping.application.dto.ProductCategoryDto;
import com.shopping.application.mapper.ProductCategoryMapper;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.service.ProductCategoryService;
import com.shopping.application.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryService = productCategoryService;
        this.productCategoryMapper = productCategoryMapper;
    }

    @PostMapping
    public void createCategory(@RequestBody ProductCategoryDto productCategoryDto){

        productCategoryService.create(productCategoryDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getProductCategory(@PathVariable String id ){
        Long productCategoryId = Long.parseLong(id);
        ProductCategoryDto productCategoryDto = productCategoryService.getProductCategory(productCategoryId)
                                            .map(productCategoryMapper::productCategoryToProductCategoryDto)
                                            .orElseThrow(NullPointerException::new);
        return ResponseEntity.ok().body(productCategoryDto);
    }
}
