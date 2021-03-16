package com.shopping.application.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private String id;

    private String totalPrice;

    private String orderStatus;

    private UserDto user;

    private Set<OrderItemDto> ordersItems;
}
