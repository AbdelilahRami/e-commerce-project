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

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.productDTOToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDTO(savedProduct);
    }

    @Transactional
    public Optional<Product> getProductById(String id) {
        UUID uuid = Helper.manageProductUUIdConversion(id);
        return uuid != null ? productRepository.findById(uuid): Optional.empty();
    }

    public Collection<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        Collection<ProductDto> productDtos = productMapper.mapProductsToProductsDTo(products);
        return productDtos;
    }

    public Optional<Product> updateProduct(ProductDto productDto, String id) {
        Product product = getProductById(id).orElseThrow(ProductNotFoundException::new);
        Product mappedProduct = productMapper.productDTOToProduct(productDto);
        Product updatedProduct = productRepository.save(mappedProduct);
        return Optional.of(updatedProduct);
    }

    public void deleteProduct(String id) {
        Product product = getProductById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(product.getId());
    }
}
