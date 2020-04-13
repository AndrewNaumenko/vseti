package com.vseti.processor.web.client;

import com.vseti.processor.dto.OrderDto;
import com.vseti.processor.dto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrderClient {
    private final RestTemplate restTemplate;
    private final String ordersUrl = "http://localhost:8086/api/v1/orders/";

    @Autowired
    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrderDto> getAllOrdersByEmail(String email) {
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                ordersUrl + "email/" + email, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {});
        return response.getBody();
    }

    public List<OrderDto> getAllOrdersByPaymentStatus(String paymentStatus) {
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                ordersUrl + "payment/" + paymentStatus, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {});
        return response.getBody();
    }

    public Double getAllOrdersTotalPriceByEmail(String email) {
        ResponseEntity<Double> response = restTemplate.exchange(
                ordersUrl + email + "/total-price", HttpMethod.GET, null,
                new ParameterizedTypeReference<Double>() {});
        return response.getBody();
    }

    public OrderDto getOrderById(long id) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public Integer getNumOrderItemsInOrder(long id){
        ResponseEntity<Integer> response = restTemplate.exchange(
                ordersUrl + id + "/length", HttpMethod.GET, null,
                new ParameterizedTypeReference<Integer>() {});
        return response.getBody();
    }

    public OrderDto addOrderItemToOrder(long orderId, OrderItemDto orderItemDto) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + orderId + "/add", HttpMethod.PUT, new HttpEntity<>(orderItemDto),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto deleteOrderItemFromOrder(long orderId, long orderItemId) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + orderId + "/delete", HttpMethod.PUT, new HttpEntity<>(orderItemId),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl, HttpMethod.POST, new HttpEntity<>(orderDto),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto payForOrder(long id) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id +"/payment-status", HttpMethod.PUT, new HttpEntity<>("PAID"),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto changeOrderStatus(long id, String orderStatus) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id + "/order-status", HttpMethod.PUT, new HttpEntity<>(orderStatus),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto changeDeliveryAddress(long id, String address) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id + "/address", HttpMethod.PUT, new HttpEntity<>(address),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto changeContactNumber(long id, String contactNumber) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id + "/contact-number", HttpMethod.PUT, new HttpEntity<>(contactNumber),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto changePaymentType(long id, String paymentType) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id + "/payment-type", HttpMethod.PUT, new HttpEntity<>(paymentType),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }
}