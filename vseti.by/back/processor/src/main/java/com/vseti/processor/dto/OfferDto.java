package com.vseti.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OfferDto {
    private long id = 0;
    private String title = "";
    private String description = "";
    private double price = 0.0;
    private String photo = "";
    private CategoryInfoDto category = new CategoryInfoDto(0, "");

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryInfoDto{
        private long id;
        private String category;
    }
}
