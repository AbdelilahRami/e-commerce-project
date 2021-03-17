package com.shopping.application.service;


import com.shopping.application.dto.OrderDto;
import com.shopping.application.dto.OrderItemDto;
import com.shopping.application.exception.OrderNotFoundException;
import com.shopping.application.exception.UserNotFoundException;
import com.shopping.application.mapper.OrderItemMapper;
import com.shopping.application.mapper.OrderMapper;
import com.shopping.application.models.Order;
import com.shopping.application.models.OrderItem;
import com.shopping.application.models.User;
import com.shopping.application.repositorie.OrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserService userService;

    public List<Order> findAll() {
        return orderRepository.findAll();

    }

    public Order placeOrder(OrderDto orderDto) throws UserNotFoundException {
        User user= userService.getById(orderDto.getUser().getId());
        Order order = orderMapper.orderDToToOrder(orderDto);
        order.setUser(user);
        return orderRepository.save(order);

    }

    public Order getById(UUID uuid) {

        Order order= orderRepository.findById(uuid).orElseThrow(OrderNotFoundException::new);

        return order;
    }

    public void delete(UUID uuid) {
        Order order= orderRepository.findById(uuid).orElseThrow(OrderNotFoundException::new);
        orderRepository.delete(order);
    }

    public Order addOrderItem(UUID uuid, OrderItemDto orderItemDto) {
        Order order= getById(uuid);
        Set<OrderItem> orderItems = order.getOrderItems();
        OrderItem orderItem= orderItemMapper.orderItemDtoToOrderItem(orderItemDto);
        orderItems.add(orderItem);
        return orderRepository.save(order);
    }
}
