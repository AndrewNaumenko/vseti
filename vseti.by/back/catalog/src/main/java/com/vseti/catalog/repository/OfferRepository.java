package com.vseti.catalog.repository;

import com.vseti.catalog.entity.Category;
import com.vseti.catalog.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByCategory(Category category);
}
