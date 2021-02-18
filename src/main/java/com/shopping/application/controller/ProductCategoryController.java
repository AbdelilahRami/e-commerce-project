package com.shopping.application.controller;

import com.shopping.application.dto.ProductCategoryDto;
import com.shopping.application.service.ProductCategoryService;
import com.shopping.application.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping
    public void createCategory(@RequestBody ProductCategoryDto productCategoryDto){

        productCategoryService.create(productCategoryDto);

    }
}
