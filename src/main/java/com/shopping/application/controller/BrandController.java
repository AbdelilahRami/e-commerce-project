package com.shopping.application.controller;

import com.shopping.application.dto.BrandDto;
import com.shopping.application.mapper.BrandMapper;
import com.shopping.application.models.Brand;
import com.shopping.application.repositorie.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final  BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    public BrandController(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto brandDto){
        Brand brand = brandMapper.brandDtoToBrand(brandDto);
        Brand savedBrand = brandRepository.save(brand);
        return new ResponseEntity<>(brandMapper.brandToBrandDto(brand), HttpStatus.OK);
    }
}
