package com.vseti.shop.invevntory.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private long id = 0;
    private String title = "";
    private String description = "";
    private double price = 0.0;
    private String photo = "";
    private String category = "";
    private long orderId = 0;
}