package com.vseti.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDto {
    private long id = 0;
    private String category = "";
    private Set<OfferInfoDto> offers = new HashSet<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class OfferInfoDto {
        private long id;
        private String title;
        private String description;
        private double price;
        private String photo;
    }
}