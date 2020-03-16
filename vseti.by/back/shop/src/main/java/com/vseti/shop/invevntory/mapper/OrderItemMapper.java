package com.vseti.shop.invevntory.mapper;

import com.vseti.shop.invevntory.dto.OrderItemDto;
import com.vseti.shop.invevntory.entity.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderItemMapper {
    private final ModelMapper mapper;

    @Autowired
    public OrderItemMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public OrderItem toEntity(OrderItemDto orderItemDto) {
        return Objects.isNull(orderItemDto) ? null : mapper.map(orderItemDto, OrderItem.class);
    }

    public OrderItemDto toDto(OrderItem entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderItemDto.class);
    }
}
