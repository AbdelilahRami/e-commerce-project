package com.shopping.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private String id;

    private String unitPrice;

    private int quantity;

    private String productId;

}
