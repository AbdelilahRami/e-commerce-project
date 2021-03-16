package com.shopping.application.mapper;

import com.shopping.application.dto.OrderItemDto;
import com.shopping.application.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", imports = {Long.class, BigDecimal.class, Integer.class, Helper.class})
public abstract class OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unitPrice", expression = "java(new BigDecimal(orderItemDto.getUnitPrice()))")
    @Mapping(target = "productId", expression = "java(Helper.manageProductUUIdConversion(orderItemDto.getProductId()))")
    public abstract OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);
}
