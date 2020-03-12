package com.vseti.shop.invevntory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false, unique = true)
    private long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems = new HashSet<>();

    @Column(nullable = false)
    private String email = "";

    @Column(nullable = false)
    private String contactNumber = "";

    @Column(nullable = false)
    private String deliveryAddress = "";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType = PaymentType.CASH;

    @Column(name = "order_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timeStamp = Calendar.getInstance();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.IN_PROCESS;

    public double getOrderPrice() {
        return orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    public int getOrderItemsNumber(){
        return orderItems.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                Objects.equals(getEmail(), order.getEmail()) &&
                Objects.equals(getTimeStamp(), order.getTimeStamp()) &&
                getPaymentStatus() == order.getPaymentStatus() &&
                getOrderStatus() == order.getOrderStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getTimeStamp(), getPaymentStatus(), getOrderStatus());
    }
}
