package com.shopping.application.mapper;


import com.shopping.application.dto.ProductCategoryDto;
import com.shopping.application.dto.ProductDto;
import com.shopping.application.models.Product;
import com.shopping.application.models.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductCategoryMapper {
    @Autowired
    private  ProductMapper productMapper;

    @Mapping(target = "id", source = "productCategoryDto.id")
    @Mapping(target = "categoryName", source= "productCategoryDto.categoryName")
    @Mapping(target = "products", expression = "java(getProductFromCategoryDTO(productCategoryDto))")
    public abstract ProductCategory productCategoryDtoToProductCategory(ProductCategoryDto productCategoryDto);


    Set<Product> getProductFromCategoryDTO(ProductCategoryDto productCategoryDto){
        Set<ProductDto> productDTOs = productCategoryDto.getProducts();
        return productDTOs != null ? mapProductsDToToProducts(productDTOs): null;

    }

    Set<Product> mapProductsDToToProducts(Set<ProductDto> productDTOs){
        return productDTOs.stream()
                .map(productDto -> productMapper.productDTOToProduct(productDto))
                .collect(Collectors.toSet());

    }

}
