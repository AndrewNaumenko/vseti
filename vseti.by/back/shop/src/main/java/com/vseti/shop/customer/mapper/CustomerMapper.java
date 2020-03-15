package com.vseti.shop.customer.mapper;

import com.vseti.shop.customer.dto.CustomerDto;
import com.vseti.shop.customer.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper {
    private final ModelMapper mapper;

    @Autowired
    public CustomerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Objects.isNull(customerDto) ? null : mapper.map(customerDto, Customer.class);
    }

    public CustomerDto toDto(Customer entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CustomerDto.class);
    }
}
