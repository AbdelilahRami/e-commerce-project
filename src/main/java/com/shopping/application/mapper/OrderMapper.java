package com.shopping.application.mapper;


import com.shopping.application.dto.OrderDto;
import com.shopping.application.dto.OrderItemDto;
import com.shopping.application.models.OrderItem;
import com.shopping.application.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Long.class, BigDecimal.class, Integer.class, Helper.class})
public abstract class OrderMapper {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public OrderItemMapper orderItemMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", expression = "java(new BigDecimal(orderDto.getTotalPrice()))")
    @Mapping(target = "user", expression = "java(userMapper.userDtoToUser(orderDto.getUser()))")
    @Mapping(target = "orderItems", expression = "java(mapOrderItemsDToToOrderItems(orderDto))")
    public abstract Order orderDToToOrder(OrderDto orderDto);


    public Set<OrderItem> mapOrderItemsDToToOrderItems(OrderDto orderDto){
        Set<OrderItemDto> orderItemDtos= orderDto.getOrdersItems();
        return orderItemDtos !=null ? getOrderItems(orderItemDtos) : null;
    }

    private Set<OrderItem> getOrderItems(Set<OrderItemDto> orderItemDtos) {
        return orderItemDtos.stream().map(orderItemMapper::orderItemDtoToOrderItem)
                .collect(Collectors.toSet());
    }
}
