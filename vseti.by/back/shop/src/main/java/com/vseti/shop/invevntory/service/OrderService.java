package com.vseti.shop.invevntory.service;


import com.vseti.shop.invevntory.entity.*;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);

    Order getOrderById(long id);

    List<Order> getAllOrders();

    List<Order> getAllOrders(String customerEmail);

    List<OrderItem> getAllOrderItemsByCategory(String customerEmail, String category);

    List<Order> getAllOrders(PaymentStatus paymentStatus);

    Order getLastOrderByEmailAndOrderStatus(String email, OrderStatus orderStatus);

    double getAllOrdersTotalPriceByEmail(String customerEmail);

    int getNumOrderItemsInOrder(long orderId);

    Order addOrderItem(long id, OrderItem item);

    Order deleteOrderItem(long id, long itemId);

    Order changeDeliveryAddress(long id, String address);

    Order changeContactNumber(long id, String contactNumber);

    Order changePaymentType(long id, PaymentType paymentType);

    Order changeOrderStatus(long id, OrderStatus orderStatus);

    Order changePaymentStatus(long id, PaymentStatus paymentStatus);

    void deleteOrderById(long id);
}