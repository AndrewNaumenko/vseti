package com.vseti.processor.web.controller;

import com.vseti.processor.dto.*;
import com.vseti.processor.transformator.Transformator;
import com.vseti.processor.web.client.CustomerClient;
import com.vseti.processor.web.client.OfferClient;
import com.vseti.processor.web.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/processor")
public class ProcessorController {
    private final CustomerClient customerClient;
    private final OfferClient offerClient;
    private final OrderClient orderClient;
    private final Transformator transformator;

    @Autowired
    public ProcessorController(CustomerClient customerClient, OfferClient offerClient, OrderClient orderClient, Transformator transformator) {
        this.customerClient = customerClient;
        this.offerClient = offerClient;
        this.orderClient = orderClient;
        this.transformator = transformator;
    }

    @PostMapping("/orders")
    public OrderDto saveOrder(@RequestBody OrderOffersDto orderOffersDto) {
        CustomerDto customerDto = customerClient.getCustomerByEmail(orderOffersDto.getEmail());
        OrderDto orderDto = transformator.transformOrderOffersToOrder(orderOffersDto);
        orderDto.setEmail(customerDto.getEmail());
        Set<OfferDto> offerDtoSet = orderOffersDto.getOffers().stream().map(offerClient::getOfferById).collect(Collectors.toSet());
        orderDto.setOrderItems(offerDtoSet.stream().map(transformator::transformOfferToOrderItem).collect(Collectors.toSet()));
        return orderClient.saveOrder(orderDto);
    }

    @PutMapping(value = "/orders/{id}/add", params = {"offerId"})
    public OrderDto addOfferToOrder(@PathVariable("id") long orderId, @RequestParam("offerId") long offerId) {
        OfferDto offerDto = offerClient.getOfferById(offerId);
        OrderItemDto orderItemDto = transformator.transformOfferToOrderItem(offerDto);
        return orderClient.addOrderItemToOrder(orderId, orderItemDto);
    }

    @PutMapping(value = "/orders/{id}/delete", params = "orderItemId")
    public OrderDto deleteOrderItemFromOrder(@PathVariable("id") long orderId, @RequestParam("orderItemId") long orderItemId) {
        return orderClient.deleteOrderItemFromOrder(orderId, orderItemId);
    }

    @GetMapping("/{email}/total-sum")
    public double getAllOrdersTotalPriceByEmail(@PathVariable("email") String email) {
        return orderClient.getAllOrdersTotalPriceByEmail(email);
    }

    @GetMapping("orders/{id}/length")
    public int getNumOrderItemsInOrder(@PathVariable("id") long id){
        return orderClient.getNumOrderItemsInOrder(id);
    }

    @GetMapping("/{email}/amount")
    public int getOrdersAmountByEmail(@PathVariable("email") String email) {
        return orderClient.getAllOrdersByEmail(email).size();
    }

    @GetMapping("/{email}/orders")
    public List<OrderDto> getAllOrdersByEmail(@PathVariable("email") String email) {
        return orderClient.getAllOrdersByEmail(email);
    }

    @GetMapping("/orders/payment/{paymentStatus}")
    public List<OrderDto> getAllOrdersByPaymentStatus(@PathVariable("paymentStatus") String paymentStatus) {
        return orderClient.getAllOrdersByPaymentStatus(paymentStatus);
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrderById(@PathVariable("id") long id) {
        return orderClient.getOrderById(id);
    }

    @PutMapping("/orders/{id}/pay")
    public OrderDto payForOrderById(@PathVariable("id") long id) {
        return orderClient.payForOrder(id);
    }

    @PutMapping("/orders/{id}/order-status")
    public OrderDto changeOrderStatus(@PathVariable("id") long id, @RequestBody String orderStatus) {
        return orderClient.changeOrderStatus(id, orderStatus);
    }

    @PutMapping("/orders/{id}/address")
    public OrderDto changeDeliveryAddress(@PathVariable("id") long id, @RequestBody String address) {
        return orderClient.changeDeliveryAddress(id, address);
    }

    @PutMapping("/orders/{id}/contact-number")
    public OrderDto changeContactNumber(@PathVariable("id") long id, @RequestBody String contactNumber) {
        return orderClient.changeContactNumber(id, contactNumber);
    }

    @PutMapping("/orders/{id}/payment-type")
    public OrderDto changePaymentType(@PathVariable("id") long id, @RequestBody String paymentType) {
        return orderClient.changePaymentType(id, paymentType);
    }
}