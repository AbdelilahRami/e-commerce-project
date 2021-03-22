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
@RequestMapping("/api/v1/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductCategoryService productCategoryService, ProductMapper productMapper) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.productMapper = productMapper;
    }

    @GetMapping
    private ResponseEntity<Collection<ProductDto>> getProducts(){
        return ResponseEntity.ok(productMapper.mapProductsToProductsDTo(productService.getAll()));
    }
    
    @GetMapping("/{id}")
    private ResponseEntity<?> getProduct(@PathVariable String id){
        logger.info("get product by id ", id);
        if(id.isEmpty() || id == null) {
            return ResponseEntity.badRequest().build();
        }
        Product product = productService.getProductById(id).orElseThrow(ProductNotFoundException::new);
        return ResponseEntity.ok(productMapper.productToProductDTO(product));
    }

    @PostMapping
    private ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto){
        logger.info("create product ", productDto);
        productCategoryService.isExistedCategory(productDto);
        Product product = productMapper.productDTOToProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToProductDTO(productService.createProduct(product)));
    }

    @PutMapping("/{id}")
    private  ResponseEntity<ProductDto> updateProduct(@PathVariable String id,@RequestBody ProductDto productDto){
        logger.info("update  product by Id ", id, productDto);
        productCategoryService.isExistedCategory(productDto);
        Product mappedProduct = productMapper.productDTOToProduct(productDto);
        return ResponseEntity.ok(productMapper.productToProductDTO(productService.updateProduct(mappedProduct, id)));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable String id){
        logger.info("delete  product by Id ", id);
        productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
