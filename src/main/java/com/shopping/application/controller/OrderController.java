package com.shopping.application.controller;


import com.shopping.application.dto.OrderDto;
import com.shopping.application.mapper.OrderMapper;
import com.shopping.application.models.Order;
import com.shopping.application.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")

public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){

        return new ResponseEntity(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDto orderDto){
        Order order = orderService.placeOrder(orderDto);
        return ResponseEntity.status(201).body(order);
    }




}
