package com.shopping.application.controller;

import com.shopping.application.dto.ProductDto;
import com.shopping.application.exception.ProductNotFoundException;
import com.shopping.application.mapper.ProductMapper;
import com.shopping.application.models.Product;
import com.shopping.application.service.ProductCategoryService;
import com.shopping.application.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

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
        logger.info("get product by id ", id);
        Product product = productService.getProductById(id).orElseThrow(ProductNotFoundException::new);
        return new ResponseEntity<>(productMapper.productToProductDTO(product), HttpStatus.OK);

    }

    @PostMapping
    private ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto){
        logger.info("create product ", productDto);
        productCategoryService.isExistedCategory(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));

    }

    @PutMapping("/{id}")
    private  ResponseEntity<ProductDto> updateProduct(@PathVariable String id,@RequestBody ProductDto productDto){
        logger.info("update  product by Id ", id, productDto);
        productCategoryService.isExistedCategory(productDto);
        ProductDto updatedProductDTO = productService.updateProduct(productDto, id).map(productMapper::productToProductDTO).orElse(null);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable String id){
        logger.info("delete  product by Id ", id);
        productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
