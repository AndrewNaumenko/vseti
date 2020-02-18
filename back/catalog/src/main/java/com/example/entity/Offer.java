package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id", nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String title = "";

    @Column(nullable = false)
    private String description = "";

    @Column(nullable = false)
    private String photo = "";

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
    //optional false - this price must be in the 'prices' table
    private Price price;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    //optional false - this category must be in the 'categories' table
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return getId() == offer.getId() &&
                Objects.equals(getTitle(), offer.getTitle()) &&
                Objects.equals(getDescription(), offer.getDescription()) &&
                Objects.equals(getPrice(), offer.getPrice()) &&
                Objects.equals(getPhoto(), offer.getPhoto()) &&
                Objects.equals(getCategory(), offer.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getPhoto(), getCategory());
    }
}
