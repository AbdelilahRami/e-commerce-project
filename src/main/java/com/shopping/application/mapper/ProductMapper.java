package com.shopping.application.mapper;

import com.shopping.application.dto.ProductDto;
import com.shopping.application.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target ="id", expression = "java(product.getId().toString())")
    @Mapping(target ="user", expression = "java(getUser(product))")
    @Mapping(target ="productCategory", expression = "java(getProductCategory(product))")
    ProductDto productToProductDTO(Product product);

    default String getUser(Product product){

        return "test_user";
    }

    default String getProductCategory(Product product){
        return "category_test";
    }



}
