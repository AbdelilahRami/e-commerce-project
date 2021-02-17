package com.shopping.application.controller;

import com.shopping.application.dto.ProductDto;
import com.shopping.application.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private List<ProductDto> getProducts(){

        return null;
    }
    @GetMapping("/{id}")
    private ProductDto getProduct(@PathVariable String id){

        return null;

    }

    @PostMapping
    private  ProductDto createProduct(@RequestBody ProductDto productDto){

        return null;
    }


}
