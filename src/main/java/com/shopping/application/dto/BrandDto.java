package com.shopping.application.dto;

import lombok.*;

import java.util.Collection;
import java.util.Set;
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {

    private Long id;
    private String brandName;
    private Collection<ProductDto> products;
}
