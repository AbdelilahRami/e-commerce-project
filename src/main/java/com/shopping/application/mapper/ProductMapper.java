package com.shopping.application.mapper;

import com.shopping.application.dto.ProductDto;
import com.shopping.application.models.Product;
import com.shopping.application.models.ProductCategory;
import com.shopping.application.models.User;
import com.shopping.application.repositorie.ProductCategoryRepository;
import com.shopping.application.repositorie.ProductRepository;
import com.shopping.application.repositorie.UserRepository;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  ProductCategoryRepository productCategoryRepository;
    @Autowired
    private UserRepository userRepository;


    @Mapping(target ="id", expression = "java(mapUUIDToString(product))")
    @Mapping(target ="user", expression = "java(getUserId(product))")
    @Mapping(target ="productCategory", expression = "java(getCategoryName(product))")
    public abstract ProductDto productToProductDTO(Product product);

    public String mapUUIDToString(Product product){
        UUID uuid = product.getId();
        return uuid != null ? uuid.toString() : null;
    }
    String getUserId(Product product){
        User user = product.getUser();
        return (user != null) ? user.getId().toString() : null;
    }
    String getCategoryName(Product product){
        return product.getProductCategory().getCategoryName();
    }


    @Mapping(target ="id", expression = "java(mapStringToUUID(productDto.getId()))")
    @Mapping(target ="user", expression = "java(getUserByProductDto(productDto))")
    @Mapping(target ="productCategory", expression = "java(getProductCategory(productDto))")
    public abstract Product productDTOToProduct(ProductDto productDto);

    public UUID mapStringToUUID(String id){
        return id!=null ? UUID.fromString(id): null;
    }

    ProductCategory getProductCategory(ProductDto productDto){
        String categoryName = productDto.getProductCategory();
        ProductCategory productCategory = productCategoryRepository.getProductCategoryByCategoryName(categoryName).orElse(null);
        return productCategory;
    }

     User getUserByProductDto(ProductDto productDto){
         String userId= productDto.getUser();
         User user = userId != null ? userRepository.findById(UUID.fromString(userId)).orElse(null): null;
         return user;
    }

    public List<ProductDto> mapProductsDToToProducts(List<Product> products){
        return  products != null ? getProductsDTOFromProducts(products): null;

    }

    private List<ProductDto> getProductsDTOFromProducts(List<Product> products) {
        return products.stream()
                .map(this::productToProductDTO)
                .collect(Collectors.toList());
    }

}
