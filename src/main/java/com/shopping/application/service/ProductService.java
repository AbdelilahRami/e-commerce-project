package com.shopping.application.service;


import com.shopping.application.dto.ProductDto;
import com.shopping.application.exception.ProductNotFoundException;
import com.shopping.application.exception.UuidConversionException;
import com.shopping.application.mapper.ProductMapper;
import com.shopping.application.models.Product;
import com.shopping.application.repositorie.ProductRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProductService {




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
        try {
            UUID uuid = UUID.fromString(id);
        } catch (Exception e) {
            throw new UuidConversionException();
        }
        Product product = productRepository.findById(UUID.fromString(id)).orElse(null);
        return Optional.ofNullable(product);
    }

    public Collection<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        Set<ProductDto> productDtos = productMapper.mapProductsToProductsDTo(products);
        return productDtos;
    }

    public Optional<Product> updateProduct(ProductDto productDto, String id) {
        Product product = getProductById(id).orElseThrow(ProductNotFoundException::new);
        Product mappedProduct = productMapper.productDTOToProduct(productDto);
        Product updatedProduct = productRepository.save(product);
        return Optional.of(updatedProduct);
    }
}
