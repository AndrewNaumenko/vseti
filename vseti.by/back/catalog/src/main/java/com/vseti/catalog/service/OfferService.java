package com.vseti.catalog.service;

import com.vseti.catalog.entity.Category;
import com.vseti.catalog.entity.Offer;

import java.util.List;

public interface OfferService {
    Offer saveOffer(Offer offer);

    Offer getOfferById(long id);

    List<Offer> getAllOffers();

    List<Offer> findOffersByCategory(Category category);

    List<Offer> findOffersByPrice(double minPrice, double maxPrice);

    Offer updateOffer(Offer update);

    Offer updateCategory(long id, Category update);

    void deleteOfferById(long id);
}