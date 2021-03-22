package com.shopping.application.service;


import com.shopping.application.dto.ProductDto;
import com.shopping.application.exception.ProductNotFoundException;
import com.shopping.application.exception.UuidConversionException;
import com.shopping.application.mapper.Helper;
import com.shopping.application.mapper.ProductMapper;
import com.shopping.application.models.Product;
import com.shopping.application.repositorie.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    
    @Transactional
    public Optional<Product> getProductById(String id) {
        UUID uuid = Helper.manageProductUUIdConversion(id);
        return uuid != null ? productRepository.findById(uuid): Optional.empty();
    }
    
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Transactional
    public Product updateProduct(Product product, String id) {
        getProductById(id).orElseThrow(ProductNotFoundException::new);
        return productRepository.save(product);
    }
    @Transactional
    public void deleteProduct(String id) {
        Product product = getProductById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(product.getId());
    }
}
