package com.vseti.shop.invevntory.repository;


import com.vseti.shop.invevntory.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
