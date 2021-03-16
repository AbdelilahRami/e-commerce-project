package com.shopping.application.service;


import com.shopping.application.dto.OrderDto;
import com.shopping.application.exception.ProductNotFoundException;
import com.shopping.application.mapper.OrderMapper;
import com.shopping.application.models.Order;
import com.shopping.application.models.OrderItem;
import com.shopping.application.models.User;
import com.shopping.application.repositorie.OrderRepository;
import com.shopping.application.repositorie.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();

    }

    public Order placeOrder(OrderDto orderDto) {
        logger.info("orderDto  "+orderDto);
        Order order = orderMapper.orderDToToOrder(orderDto);
        User user= userRepository.findById(order.getUser().getId()).orElseThrow(ProductNotFoundException::new);
        order.setUser(user);
        return orderRepository.save(order);

    }
}
