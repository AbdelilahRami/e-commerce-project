package com.shopping.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopping.application.controller.exception.ProductCategoryExceptionController;
import com.shopping.application.dto.ProductCategoryDto;
import com.shopping.application.dto.UserDto;
import com.shopping.application.mapper.ProductCategoryMapper;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.service.ProductCategoryService;

public class ProductCategoryControllerTest {

    private ProductCategory productCategory;
    private ProductCategoryDto productCategoryDto;
    private ProductCategoryService productCategoryService;
    private ProductCategoryMapper productCategoryMapper;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule());
    
    @BeforeEach
    void  setUp() {
        productCategory = new ProductCategory();
        productCategory.setId(1L);
        productCategory.setCategoryName("test");
        
        productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setId(1L);
        productCategoryDto.setCategoryName("test");
        
        productCategoryService = mock(ProductCategoryService.class);
        productCategoryMapper = mock(ProductCategoryMapper.class);
        ProductCategoryController controller = new ProductCategoryController(productCategoryService, productCategoryMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).
                setControllerAdvice(new ProductCategoryExceptionController()).build();
    }
    
    /*--------------------create--------------------*/
    @Test
    void createShouldReturnOkAndTheProductCategory() throws Exception {
        productCategoryDto.setId(0L);
        productCategory.setId(0L);
        when(productCategoryService.create(productCategoryDto)).thenReturn(productCategory);
        when(productCategoryMapper.productCategoryToProductCategoryDto(productCategory)).thenReturn(productCategoryDto);

        String mockResult = mockMvc
                .perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(productCategoryDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(productCategoryService, times(1)).create(productCategoryDto);
        verifyNoMoreInteractions(productCategoryService);

        ProductCategoryDto result = objectMapper.readValue(mockResult, ProductCategoryDto.class);
        assertThat(result).isEqualTo(productCategoryDto);
    }
    
    /*----------------------getById------------------------------*/
    @Test
    void getByIdShouldReturnProductCategory() throws Exception {
        Long id = 1L;

        when(productCategoryService.getProductCategory(id)).thenReturn(Optional.of(productCategory));
        when(productCategoryMapper.productCategoryToProductCategoryDto(productCategory)).thenReturn(productCategoryDto);

        String mockResult = mockMvc.perform(get("/api/v1/categories/{id}", id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductCategoryDto result = objectMapper.readValue(mockResult, ProductCategoryDto.class);
        assertThat(result).isEqualTo(productCategoryDto);
    }
    
    @Test
    void getByIdShouldRThrowNullPointeuException() throws Exception {
        Long id = 41L;

        when(productCategoryService.getProductCategory(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/categories/{id}", id))
                .andExpect(status().isNotFound());
    }
}
