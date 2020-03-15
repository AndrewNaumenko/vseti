package com.vseti.shop.invevntory.controller;

import com.vseti.shop.invevntory.dto.OrderDto;
import com.vseti.shop.invevntory.dto.OrderItemDto;
import com.vseti.shop.invevntory.entity.*;
import com.vseti.shop.invevntory.mapper.OrderItemMapper;
import com.vseti.shop.invevntory.mapper.OrderMapper;
import com.vseti.shop.invevntory.service.DefaultOrderService;
import com.vseti.shop.invevntory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderController(DefaultOrderService orderService, OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(orderMapper.toDto(savedOrder), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findOrder(@PathVariable("id") long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @GetMapping("/{id}/length")
    public ResponseEntity<Integer> getNumOrderItemsInOrder(@PathVariable("id") long id){
        return new ResponseEntity<>(orderService.getNumOrderItemsInOrder(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDto> orderDtoList = orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<OrderDto>> findAllOrdersByEmail(@PathVariable("email") String email) {
        List<Order> orders = orderService.getAllOrders(email);
        List<OrderDto> orderDtoList = orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("/payment/{paymentStatus}")
    public ResponseEntity<List<OrderDto>> findAllOrdersByPaymentStatus(@PathVariable("paymentStatus") String paymentStatus) {
        List<Order> orders = orderService.getAllOrders(PaymentStatus.valueOf(paymentStatus));
        List<OrderDto> orderDtoList = orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/email/{email}", params = {"order-status"})
    public ResponseEntity<OrderDto> findOrderByOrderStatus(@PathVariable("email") String email, @RequestParam("order-status") String orderStatus) {
        Order order = orderService.getLastOrderByEmailAndOrderStatus(email, OrderStatus.valueOf(orderStatus));
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @GetMapping("/{email}/total-price")
    public ResponseEntity<Double> getAllOrdersTotalPriceByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(orderService.getAllOrdersTotalPriceByEmail(email), HttpStatus.OK);
    }

    @GetMapping(value = "/email/{email}", params = {"category"})
    public ResponseEntity<List<OrderItemDto>> findOrderItemsByCategory(@PathVariable("email") String customerEmail, @RequestParam("category") String category) {
        List<OrderItem> orderItemList = orderService.getAllOrderItemsByCategory(customerEmail, category);
        List<OrderItemDto> orderItemDtoList = orderItemList.stream().map(orderItemMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderItemDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}/add")
    public ResponseEntity<OrderDto> addOrderItem(@PathVariable("id") long id, @RequestBody OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemMapper.toEntity(orderItemDto);
        Order updatedOrder = orderService.addOrderItem(id, orderItem);
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/delete")
    public ResponseEntity<OrderDto> deleteOrderItem(@PathVariable("id") long id, @RequestBody long itemId) {
        Order order = orderService.getOrderById(id);
        if(order.getPaymentStatus() != PaymentStatus.PAID) {
            Order updatedOrder = orderService.deleteOrderItem(id, itemId);
            return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
        }
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/order-status")
    public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable("id") long id, @RequestBody String orderStatus) {
        Order updatedOrder = orderService.changeOrderStatus(id, OrderStatus.valueOf(orderStatus));
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/payment-status")
    public ResponseEntity<OrderDto> changePaymentStatus(@PathVariable("id") long id, @RequestBody String paymentStatus) {
        Order updatedOrder = orderService.changePaymentStatus(id, PaymentStatus.valueOf(paymentStatus));
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/address")
    public ResponseEntity<OrderDto> changeDeliveryAddress(@PathVariable("id") long id, @RequestBody String address) {
        Order updatedOrder = orderService.changeDeliveryAddress(id, address);
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/contact-number")
    public ResponseEntity<OrderDto> changeContactNumber(@PathVariable("id") long id, @RequestBody String contactNumber) {
        Order updatedOrder = orderService.changeContactNumber(id, contactNumber);
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/payment-type")
    public ResponseEntity<OrderDto> changePaymentType(@PathVariable("id") long id, @RequestBody String paymentType) {
        Order updatedOrder = orderService.changePaymentType(id, PaymentType.valueOf(paymentType));
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
