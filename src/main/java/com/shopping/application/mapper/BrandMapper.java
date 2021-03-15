package com.shopping.application.mapper;

import com.shopping.application.dto.BrandDto;
import com.shopping.application.dto.ProductDto;
import com.shopping.application.models.Brand;
import com.shopping.application.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class BrandMapper {
    @Autowired
    private ProductMapper productMapper;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "brandName", source = "brandName")
    @Mapping(target = "products", expression = "java(getProductsDto(brand))")
    public abstract BrandDto brandToBrandDto(Brand brand);

    public Collection<ProductDto> getProductsDto(Brand brand){
        Collection<Product> products = brand.getProducts();
        Collection<ProductDto> productDtos = productMapper.mapProductsToProductsDTo(products);
        return  productDtos;
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "brandName", source = "brandName")
    @Mapping(target = "products", expression = "java(getProducts(brandDto))")
    public abstract Brand brandDtoToBrand(BrandDto brandDto);


    public Collection<Product> getProducts(BrandDto brandDto) {
        Collection<ProductDto> productDtos = brandDto.getProducts();
        Collection<Product> products = productMapper.mapProductsDToToProducts(productDtos);
        return  products;
    }

}
