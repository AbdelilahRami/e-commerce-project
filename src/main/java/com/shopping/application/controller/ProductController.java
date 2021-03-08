package com.shopping.application.controller;

import com.shopping.application.dto.ProductDto;
import com.shopping.application.exception.NoCategoryException;
import com.shopping.application.exception.ProductNotFoundException;
import com.shopping.application.mapper.ProductMapper;
import com.shopping.application.models.Product;
import com.shopping.application.service.ProductCategoryService;
import com.shopping.application.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final String NO_CATEGORY_FOUND_404 = "N0_CATEGORY_FOUND_404";

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductCategoryService productCategoryService, ProductMapper productMapper) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.productMapper = productMapper;
    }

    @GetMapping
    private Collection<ProductDto> getProducts(){

        return productService.getAll();
    }
    @GetMapping("/{id}")
    private ResponseEntity<ProductDto> getProduct(@PathVariable String id){
        Product product = productService.getProductById(id).orElseThrow(ProductNotFoundException::new);
        return new ResponseEntity<>(productMapper.productToProductDTO(product), HttpStatus.OK);

    }

    @PostMapping
    private ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto){
        checkProductCategoryExistence(productDto, NO_CATEGORY_FOUND_404);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));

    }

    @PutMapping("/{id}")
    private  ResponseEntity<ProductDto> updateProduct(@PathVariable String id,@RequestBody ProductDto productDto){
        checkProductCategoryExistence(productDto,  NO_CATEGORY_FOUND_404);
        ProductDto updatedProductDTO = productService.updateProduct(productDto, id).map(productMapper::productToProductDTO).orElse(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProductDTO);
    }



    @DeleteMapping("/{id}")
    private void deleteProduct(@PathVariable String id, @RequestBody ProductDto productDto){

    }


    private void checkProductCategoryExistence(ProductDto productDto, String category_found_404) {
        if (!productCategoryService.isExistedCategory(productDto)) {
            throw new NoCategoryException(category_found_404);
        }
    }

}
