package com.shopping.application.dto;

import lombok.*;

import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto {

    private long id;
    private String categoryName;
    private Set<ProductDto> products;
}
