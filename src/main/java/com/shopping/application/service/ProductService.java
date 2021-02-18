package com.shopping.application.service;


import com.shopping.application.dto.ProductDto;
import com.shopping.application.mapper.ProductMapper;
import com.shopping.application.models.Product;
import com.shopping.application.repositorie.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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
        Product product= productMapper.productDTOToProduct(productDto);
        Product savedProduct= productRepository.save(product);
        return productMapper.productToProductDTO(savedProduct);
    }

    public ProductDto getProductById(String id) {
        Product product= productRepository.findById(UUID.fromString(id)).orElse(null);
        return productMapper.productToProductDTO(product);

    }

    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = productMapper.mapProductsDToToProducts(products);
        return productDtos;
    }
}
