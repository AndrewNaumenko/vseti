package com.vseti.catalog.mapper;

import com.vseti.catalog.dto.CategoryDto;
import com.vseti.catalog.entity.Category;
import com.vseti.catalog.entity.Offer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryMapper {
    private final ModelMapper mapper;

    @Autowired
    public CategoryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Category toEntity(CategoryDto categoryDto) {
        Category category = Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, Category.class);
        for (Offer offer: category.getOffers()){
            offer.setCategory(category);
            offer.getPrice().setOffer(offer);
        }
        return category;
    }

    public CategoryDto toDto(Category entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CategoryDto.class);
    }
}