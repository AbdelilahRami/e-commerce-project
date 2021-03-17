package com.shopping.application.service;

import com.shopping.application.dto.OrderDto;
import com.shopping.application.dto.UserDto;
import com.shopping.application.exception.OrderNotFoundException;
import com.shopping.application.exception.UserNotFoundException;
import com.shopping.application.mapper.OrderMapper;
import com.shopping.application.models.Order;
import com.shopping.application.models.OrderStatus;
import com.shopping.application.models.User;
import com.shopping.application.repositorie.OrderRepository;
import com.shopping.application.repositorie.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderMapper orderMapper;
    @MockBean
    private UserService userService;

    private Order order1= Order.builder().totalPrice(new BigDecimal(200)).build();
    private Order order2= Order.builder().totalPrice(new BigDecimal(200)).build();
    private Order order3= Order.builder().totalPrice(new BigDecimal(200)).build();
    private List<Order> orderList= new ArrayList<>();
    private UserDto userDto = UserDto.builder().firstName("John").lastName("Doe").build();
    private User user= User.builder().firstName("John").lastName("Doe").build();
    private Order order;
    private OrderDto orderDto;

    @BeforeEach
    void setUp() {
        orderList.addAll(Stream.of(order1,order2,order3).collect(Collectors.toList()));
        orderDto = OrderDto.builder().totalPrice("30").orderStatus("PENDING").user(userDto).build();
        order= Order.builder().totalPrice(new BigDecimal("30")).orderStatus(OrderStatus.PENDING).build();
    }

    @Test
    void findAll() {
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> orders = orderService.findAll();
        Assertions.assertEquals(3, orders.size());
    }

    @Test
    void placeOrder() throws UserNotFoundException {
        when(userService.getById((orderDto.getUser().getId()))).thenReturn(user);
        when(orderMapper.orderDToToOrder(orderDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        Order returnedOrder = orderService.placeOrder(orderDto);
        Assertions.assertEquals(returnedOrder, order);

}

    @Test
    @DisplayName("when repository returns order it should returns the order")
    void should_return_order_when_its_found_in_repository() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        Order returndOrder = orderService.getById(id);
        Assertions.assertEquals(returndOrder, order);
    }
    @Test
    @DisplayName("given an id it should returns the order")
    void should_throw_exception_when_its_not_found_in_repository() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findById(id)).thenThrow(OrderNotFoundException.class);
        Assertions.assertThrows(OrderNotFoundException.class,() -> orderService.getById(id));
    }
    @Test
    void delete() {
    }

    @Test
    void addOrderItem() {
    }
}