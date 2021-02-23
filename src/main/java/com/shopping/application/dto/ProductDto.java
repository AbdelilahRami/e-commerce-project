package com.shopping.application.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String productName;
    private String description;
    private String brandName;
    private double price;
    private int quantity;
    private String user;
    private String productCategory;

    public boolean hasCategoryDto(){
        return this.productCategory != null && this.productCategory.isEmpty();
    }

}
