package com.vseti.processor.web.client;

import com.vseti.processor.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerClient {
    private final RestTemplate restTemplate;
    private final String customersUrl = "http://localhost:8086/api/v1/customers/";

    @Autowired
    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerDto getCustomerByEmail(String email) {
        ResponseEntity<CustomerDto> response = restTemplate.exchange(
                customersUrl + email, HttpMethod.GET, null,
                new ParameterizedTypeReference<CustomerDto>() {});
        return response.getBody();
    }
}