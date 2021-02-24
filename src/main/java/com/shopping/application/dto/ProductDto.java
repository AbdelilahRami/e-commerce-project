package com.shopping.application.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;

    @NotEmpty(message = "Please provide a product name")
    private String productName;
    private String description;
    @NotEmpty(message = "Please provide a brand name")
    private String brandName;
    private double price;
    private int quantity;
    private String user;
    private String productCategory;

    public boolean hasCategoryDto(){
        return this.productCategory != null && !this.productCategory.isEmpty();
    }

}
