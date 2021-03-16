package com.shopping.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopping.application.dto.BrandDto;
import com.shopping.application.mapper.BrandMapper;
import com.shopping.application.models.Brand;
import com.shopping.application.repositorie.BrandRepository;

public class BrandControllerTest {

    private Brand brand;
    private BrandDto brandDto;
    private MockMvc mockMvc;
    private BrandMapper mapper;
    private BrandRepository repository;
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule());
    
    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setId(0L);
        brand.setBrandName("test");
        
        brandDto = new BrandDto();
        brandDto.setId(0L);
        brandDto.setBrandName("test");
        
        repository = mock(BrandRepository.class);
        mapper = mock(BrandMapper.class);
        BrandController controller = new BrandController(repository,mapper);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    /*--------------------create--------------------*/
    @Test
    void createShouldReturnOkAndTheBrand() throws Exception {
        when(mapper.brandDtoToBrand(brandDto)).thenReturn(brand);
        when(repository.save(brand)).thenReturn(brand);
        when(mapper.brandToBrandDto(brand)).thenReturn(brandDto);

        String mockResult = mockMvc
                .perform(post("/api/v1/brands")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(repository, times(1)).save(brand);
        verifyNoMoreInteractions(repository);

        BrandDto result = objectMapper.readValue(mockResult, BrandDto.class);
        assertThat(result).isEqualTo(brandDto);
    }
}
