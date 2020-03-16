package com.vseti.catalog.web.controller;

import com.vseti.catalog.dto.CategoryDto;
import com.vseti.catalog.entity.Category;
import com.vseti.catalog.mapper.CategoryMapper;
import com.vseti.catalog.service.CategoryService;
import com.vseti.catalog.service.DefaultCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(DefaultCategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<List<CategoryDto>> saveCategories(@RequestBody List<CategoryDto> categoryDtoList) {
        Set<Category> categories = new HashSet<>();
        categoryDtoList.forEach(categoryDto -> categories.add(categoryMapper.toEntity(categoryDto)));
        Set<Category> savedCategories = categoryService.saveCategories(categories);
        List<CategoryDto> savedCategoryDtoList = savedCategories.stream().map(categoryMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(savedCategoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDto> findCategory(@PathVariable("id") long id) {
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryDto> findCategory(@PathVariable("name") String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoriesDtoList = categories.stream().map(categoryMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(categoriesDtoList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        Category updatedCategory = categoryService.updateCategoryName(categoryDto.getId(), categoryDto.getCategory());
        CategoryDto updatedCategoryDto = categoryMapper.toDto(updatedCategory);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}