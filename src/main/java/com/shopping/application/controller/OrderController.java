package com.shopping.application.controller;


import com.shopping.application.dto.OrderDto;
import com.shopping.application.dto.OrderItemDto;
import com.shopping.application.exception.UserNotFoundException;
import com.shopping.application.mapper.Helper;
import com.shopping.application.mapper.OrderMapper;
import com.shopping.application.models.Order;
import com.shopping.application.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException {
        Order order = orderService.placeOrder(orderDto);
        return ResponseEntity.status(201).body(order);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<Order> addOrderItem(@PathVariable String id, @RequestBody OrderItemDto orderItemDto){
        UUID uuid = Helper.manageUUIDConversion(id);
        Order order = orderService.addOrderItem(uuid,orderItemDto);
        return ResponseEntity.status(200).body(order);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id){
        UUID uuid = Helper.manageUUIDConversion(id);
        Order order = orderService.getById(uuid);
        return ResponseEntity.status(200).body(order);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteOrder(@PathVariable String id){
        UUID uuid = Helper.manageUUIDConversion(id);
        orderService.delete(uuid);
        return  ResponseEntity.status(204).build();

    }



}
