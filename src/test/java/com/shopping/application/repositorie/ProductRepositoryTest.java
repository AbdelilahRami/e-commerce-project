package com.shopping.application.repositorie;

import com.shopping.application.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private UUID uuid;

    @BeforeEach
    public void setUp(){
        product= Product.builder().productName("productName").brandName("Nike").build();
        productRepository.save(product);
        uuid= UUID.fromString("c7029154-cfe3-48df-9a4c-58d5d8e03e2c");

    }

    @Test
    void findByBrandName() {
        Product retrievedProduct = productRepository.findByBrandName("Nike");
        Assertions.assertEquals(product ,retrievedProduct);

    }

    @Test
    void should_return_product() {
        Product retrievedProduct = productRepository.findById(product.getId()).orElse(null);
        Assertions.assertEquals(product ,retrievedProduct);
    }
    @Test
    void not_found_product_uuid_should_return_null() {
        Product retrievedProduct = productRepository.findById(uuid).orElse(null);
        Assertions.assertNull(retrievedProduct);
    }



    @Test
    void findAllByProductCategory() {
    }
}