package com.shopping.application.mapper;

import com.shopping.application.dto.BrandDto;
import com.shopping.application.dto.ProductDto;
import com.shopping.application.models.Brand;
import com.shopping.application.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class BrandMapper {
    @Autowired
    private ProductMapper productMapper;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "brandName", source = "brandName")
    @Mapping(target = "products", expression = "java(getProductsDto(brand))")
    public abstract BrandDto brandToBrandDto(Brand brand);

    public Set<ProductDto> getProductsDto(Brand brand){
        Set<Product> products = brand.getProducts();
        Set<ProductDto> productDtos = productMapper.mapProductsToProductsDTo(products);
        return  productDtos;
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "brandName", source = "brandName")
    @Mapping(target = "products", expression = "java(getProducts(brandDto))")
    public abstract Brand brandDtoToBrand(BrandDto brandDto);


    public Set<Product> getProducts(BrandDto brandDto){
        Set<ProductDto> productDtos = brandDto.getProducts();
        Set<Product> products = productMapper.mapProductsDToToProducts(productDtos);
        return  products;
    }

}
