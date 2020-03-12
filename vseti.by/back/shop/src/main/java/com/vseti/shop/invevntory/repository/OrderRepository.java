package com.vseti.shop.invevntory.repository;

import com.vseti.shop.invevntory.entity.Order;
import com.vseti.shop.invevntory.entity.OrderStatus;
import com.vseti.shop.invevntory.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByEmail(String email);

    List<Order> findAllByPaymentStatus(PaymentStatus paymentStatus);

    List<Order> findAllByEmailAndOrderStatus(String email, OrderStatus orderStatus);
}
