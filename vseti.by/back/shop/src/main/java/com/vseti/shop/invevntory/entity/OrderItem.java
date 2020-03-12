package com.vseti.shop.invevntory.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String title = "";

    @Column(nullable = false)
    private String description = "";

    @Column(nullable = false)
    private double price = 0.0;

    @Column(nullable = false)
    private String photo = "";

    @Column(nullable = false)
    private String category = "";

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return getId() == orderItem.getId() &&
                Double.compare(orderItem.getPrice(), getPrice()) == 0 &&
                Objects.equals(getTitle(), orderItem.getTitle()) &&
                Objects.equals(getDescription(), orderItem.getDescription()) &&
                Objects.equals(getCategory(), orderItem.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getCategory());
    }
}
