package com.shopping.application.mapper;

import com.shopping.application.dto.ProductDto;
import com.shopping.application.models.Product;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.models.User;
import com.shopping.application.repositorie.ProductCategoryRepository;
import com.shopping.application.repositorie.ProductRepository;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  ProductCategoryRepository productCategoryRepository;


    //@Mapping(target ="id", expression = "java(product.getId().toString())")
    @Mapping(target ="user", expression = "java(getUserName(product))")
    @Mapping(target ="productCategory", expression = "java(getCategoryName(product))")
    public abstract ProductDto productToProductDTO(Product product);

    //@Mapping(target ="id", expression = "java(java.util.UUID.fromString(productDto.getId()))")
    @Mapping(target ="userId", expression = "java(getUserByUsername(productDto))")
    @Mapping(target ="productCategory", expression = "java(getProductCategory(productDto))")
    public abstract Product productDTOToProduct(ProductDto productDto);

     String getUserName(Product product){
        User user = product.getUserId();
        return user.getFirstName();
    }
     User getUserByUsername(ProductDto productDto){
         String userName= productDto.getUser();
         return User.builder().firstName("username").build();
    }

     String getCategoryName(Product product){
        return product.getProductCategory().getCategoryName();
    }

     ProductCategory getProductCategory(ProductDto productDto){
         String categoryName = productDto.getProductCategory();
         ProductCategory productCategory = productCategoryRepository.getProductCategoryByCategoryName(categoryName).orElse(null);
         return productCategory;
    }

    public List<ProductDto> mapProductsDToToProducts(List<Product> products){
        return  products != null ? getProductsDTOFromProducts(products): null;

    }

    private List<ProductDto> getProductsDTOFromProducts(List<Product> products) {
        return products.stream()
                .map(product -> productToProductDTO(product))
                .collect(Collectors.toList());
    }

}
