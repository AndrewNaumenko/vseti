package com.vseti.processor.transformator;

import com.vseti.processor.dto.OfferDto;
import com.vseti.processor.dto.OrderDto;
import com.vseti.processor.dto.OrderItemDto;
import com.vseti.processor.dto.OrderOffersDto;
import org.springframework.stereotype.Component;

@Component
public class Transformator {
    public OrderItemDto transformOfferToOrderItem(OfferDto offerDto){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setCategory(offerDto.getCategory().getCategory());
        orderItemDto.setTitle(offerDto.getTitle());
        orderItemDto.setDescription(offerDto.getDescription());
        orderItemDto.setPrice(offerDto.getPrice());
        orderItemDto.setPhoto(offerDto.getPhoto());
        return orderItemDto;
    }

    public OrderDto transformOrderOffersToOrder(OrderOffersDto orderOffersDto){
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail(orderOffersDto.getEmail());
        orderDto.setId(orderOffersDto.getId());
        orderDto.setDeliveryAddress(orderOffersDto.getDeliveryAddress());
        orderDto.setContactNumber(orderOffersDto.getContactNumber());
        orderDto.setPaymentType(orderOffersDto.getPaymentType());
        orderDto.setOrderStatus(orderOffersDto.getOrderStatus());
        orderDto.setPaymentStatus(orderOffersDto.getPaymentStatus());
        orderDto.setTimeStamp(orderOffersDto.getTimeStamp());
        return orderDto;
    }
}