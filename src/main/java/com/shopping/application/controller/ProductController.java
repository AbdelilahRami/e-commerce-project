package com.shopping.application.controller;

import com.shopping.application.dto.ProductDto;
import com.shopping.application.service.ProductCategoryService;
import com.shopping.application.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;


    public ProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    private List<ProductDto> getProducts(){

        return productService.getAll();
    }
    @GetMapping("/{id}")
    private ProductDto getProduct(@PathVariable String id){

        return productService.getProductById(id);

    }

    @PostMapping
    private ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
       if(!productDto.hasCategoryDto() | !productCategoryService.isExistedCategory(productDto.getProductCategory())){
           throw new NullPointerException("N0_CATEGORY_FOUND_404");
       }
       return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));

    }


}
