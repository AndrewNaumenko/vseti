package com.vseti.shop.invevntory.service;

import com.vseti.shop.invevntory.entity.*;
import com.vseti.shop.invevntory.repository.OrderItemRepository;
import com.vseti.shop.invevntory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        Optional<Order> foundedOrder = this.orderRepository.findById(order.getId());
        return foundedOrder.orElseGet(() -> this.orderRepository.save(order));
    }

    @Override
    public Order getOrderById(long id) {
        Optional<Order> foundedOrder = this.orderRepository.findById(id);
        if(foundedOrder.isPresent()){
            return foundedOrder.get();
        } else {
            throw new RuntimeException("smth");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrders(String customerEmail) {
        return this.orderRepository.findAllByEmail(customerEmail);
    }

    @Override
    public List<Order> getAllOrders(PaymentStatus paymentStatus){
        return this.orderRepository.findAllByPaymentStatus(paymentStatus);
    }

    @Override
    public Order getLastOrderByEmailAndOrderStatus(String email, OrderStatus orderStatus) {
        List<Order> orders = this.orderRepository.findAllByEmailAndOrderStatus(email, orderStatus);
        if(orders.size() == 0){
            throw new RuntimeException("smth");
        }
        return orders.get(orders.size() - 1);
    }

    @Override
    public double getAllOrdersTotalPriceByEmail(String customerEmail){
        List<Order> orders = getAllOrders(customerEmail);
        return orders.stream().mapToDouble(Order::getOrderPrice).sum();
    }

    @Override
    public int getNumOrderItemsInOrder(long orderId) {
        return getOrderById(orderId).getOrderItems().size();
    }

    @Override
    public List<OrderItem> getAllOrderItemsByCategory(String customerEmail, String category) {
        List<Order> customerOrders = this.orderRepository.findAllByEmail(customerEmail);
        List<OrderItem> orderItems = new ArrayList<>();
        customerOrders.forEach(order -> order.getOrderItems().forEach(orderItem -> {
            if (orderItem.getCategory().equals(category)) {
                orderItems.add(orderItem);
            }})
        );
        return orderItems;
    }

    @Override
    public Order addOrderItem(long id, OrderItem item) {
        Order foundedOrder = getOrderById(id);
        item.setOrder(foundedOrder);
        foundedOrder.getOrderItems().add(item);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public Order deleteOrderItem(long id, long itemId) {
        Order foundedOrder = getOrderById(id);
        for(OrderItem orderItem: foundedOrder.getOrderItems()){
            if(orderItem.getId() == itemId){
                foundedOrder.getOrderItems().remove(orderItem);
                this.orderItemRepository.deleteById(itemId);
                break;
            }
        }
        return foundedOrder;
    }

    @Override
    public Order changeDeliveryAddress(long id, String address) {
        Order foundedOrder = getOrderById(id);
        foundedOrder.setDeliveryAddress(address);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public Order changeContactNumber(long id, String contactNumber) {
        Order foundedOrder = getOrderById(id);
        foundedOrder.setContactNumber(contactNumber);
        return this.orderRepository.save(foundedOrder);    }

    @Override
    public Order changePaymentType(long id, PaymentType paymentType) {
        Order foundedOrder = getOrderById(id);
        foundedOrder.setPaymentType(paymentType);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public Order changeOrderStatus(long id, OrderStatus orderStatus) {
        Order foundedOrder = getOrderById(id);
        foundedOrder.setOrderStatus(orderStatus);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public Order changePaymentStatus(long id, PaymentStatus paymentStatus) {
        Order foundedOrder = getOrderById(id);
        foundedOrder.setPaymentStatus(paymentStatus);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public void deleteOrderById(long id) {
        this.orderRepository.deleteById(id);
    }
}