package com.shopping.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.application.controller.exception.ProductExceptionController;
import com.shopping.application.dto.ProductDto;
import com.shopping.application.mapper.ProductMapper;
import com.shopping.application.models.Product;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.service.ProductCategoryService;
import com.shopping.application.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;
    @Mock
    ProductMapper productMapper;

    @Mock
    private ProductCategoryService productCategoryService;
    private JacksonTester<Collection<ProductDto>> jsonProductDtos;
    private JacksonTester<ProductDto> jsonProductDto;
    private JacksonTester<Product> jsonProduct;

    private ProductDto productDto1 = ProductDto.builder().productName("product1").productCategory("category1").brandName("Nike").build();
    private ProductDto productDto2 = ProductDto.builder().productName("product2").productCategory("category1").brandName("Nike").build();
    private ProductDto productDto3 = ProductDto.builder().productName("product3").productCategory("category1").brandName("Nike").build();
    private ProductDto productDto4 = ProductDto.builder().productName("product4").productCategory("category1").brandName("Nike").build();
    private Collection<ProductDto> productDtos = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        productDtos.addAll(Stream.of(productDto1, productDto2, productDto3, productDto4).collect(Collectors.toList()));
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new ProductExceptionController()).build();
    }

    @Test
    public void testFindAll() throws Exception {

        Mockito.when(productService.getAll()).thenReturn(productDtos);
        //Mockito.when(productCategoryService.isExistedCategory(productDto1)).thenReturn(true);
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/products/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assertions.assertEquals(response.getContentAsString(), jsonProductDtos.write(productDtos).getJson());
    }

    @Test
    public void addProduct() throws Exception {

        Mockito.when(productService.createProduct(productDto1)).thenReturn(productDto1);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductDto.write(productDto1).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void updateProduct() throws Exception {
        String uuid= UUID.randomUUID().toString();
        Product product= Product.builder().productName("Name").description("description").quantity(100).build();
        Mockito.when(productService.updateProduct(productDto1, uuid)).thenReturn(Optional.of(product));
        Mockito.when(productMapper.productToProductDTO(product)).thenReturn(productDto1);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/products/"+uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductDto.write(productDto1).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonProductDto.write(productDto1).getJson());

    }

    @Test
    public void deleteProduct() throws Exception {
        String id= UUID.randomUUID().toString();
        doNothing().when(productService).deleteProduct(id);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/products/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());


    }

}