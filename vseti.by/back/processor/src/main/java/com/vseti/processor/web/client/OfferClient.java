package com.vseti.processor.web.client;

import com.vseti.processor.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OfferClient {
    private final RestTemplate restTemplate;
    private final String catalogUrl = "http://localhost:8085/api/v1/offers/";

    @Autowired
    public OfferClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OfferDto getOfferById(long offerId) {
        ResponseEntity<OfferDto> response = restTemplate.exchange(
                catalogUrl+ offerId, HttpMethod.GET, null,
                new ParameterizedTypeReference<OfferDto>() {});
        return response.getBody();
    }
}