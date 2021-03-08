package com.shopping.application.repositorie;

import com.shopping.application.models.Brand;
import com.shopping.application.models.Product;
import com.shopping.application.models.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    private Brand brand= Brand.builder().brandName("Nike").build();
    private ProductCategory productCategory = ProductCategory.builder().categoryName("Clothes").build();
    private Product product1 = Product.builder().productName("product1").brandName(brand).productCategory(productCategory).build();
    private Product product2 = Product.builder().productName("product2").productCategory(productCategory).build();
    private Product product3 = Product.builder().productName("product3").productCategory(productCategory).build();
    private Product product4 = Product.builder().productName("product4").productCategory(productCategory).build();
    private List<Product> products = new ArrayList<>();
    private UUID wrongUuid= UUID.fromString("c7029154-cfe3-48df-9a4c-58d5d8e03e2c");

    @BeforeEach
    public void setUp(){
        products.addAll(Stream.of(product1, product2, product3, product4).collect(Collectors.toList()));
        productRepository.save(product1);
    }


    @Test
    @DisplayName("when category is invalid it should return empty list")
    void given_all_products() {
        productRepository.saveAll(products);
        List<Product> retrievedProducts= productRepository.findAllByProductCategory(null);
        Assertions.assertEquals(retrievedProducts.size(),0);
    }


    @Test
    @DisplayName("find a product by it's brand")
    void given_a_valid_brandName_then_return_product() {
        Product retrievedProduct = productRepository.findByBrandName(brand);
        Assertions.assertEquals(product1 ,retrievedProduct);

    }

    @Test
    @DisplayName("find a product by it's uuid")
    void given_a_correct_uuid_then_return_product() {
        Product retrievedProduct = productRepository.findById(product1.getId()).orElse(null);
        Assertions.assertEquals(product1 ,retrievedProduct);
    }

    @Test
    @DisplayName("find a product by a wrong uuid")
    void given_a_wrong_uuid_then_return_null() {
        Product retrievedProduct = productRepository.findById(wrongUuid).orElse(null);
        Assertions.assertNull(retrievedProduct);
    }

    @Test
    @DisplayName("find a product by it's product category")
    void given_product_category_then_return_its_products() {
        productRepository.saveAll(products);
        List<Product> retrievedProducts= productRepository.findAllByProductCategory(productCategory);
        Assertions.assertEquals(products, retrievedProducts);
        Assertions.assertEquals(retrievedProducts.size(),4);
    }

    @Test
    @DisplayName("when category is invalid it should return empty list")
    void given_invalid_category_then_return_empty_list() {
        productRepository.saveAll(products);
        List<Product> retrievedProducts= productRepository.findAllByProductCategory(null);
        Assertions.assertEquals(retrievedProducts.size(),0);
    }


}