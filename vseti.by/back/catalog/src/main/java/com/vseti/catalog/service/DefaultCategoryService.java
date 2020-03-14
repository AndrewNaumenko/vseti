package com.vseti.catalog.service;


import com.vseti.catalog.entity.Category;
import com.vseti.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultCategoryService implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        Optional<Category> foundedCategory = this.categoryRepository.findByCategory(category.getCategory());
        return foundedCategory.orElseGet(() -> this.categoryRepository.save(category));
    }

    @Override
    public Set<Category> saveCategories(Set<Category> categories) {
        return categories.stream().map(this::saveCategory).collect(Collectors.toSet());
    }

    @Override
    public Category getCategoryById(long id) {
        Optional<Category> foundedCategory = this.categoryRepository.findById(id);
        if(foundedCategory.isPresent()){
            return foundedCategory.get();
        }else {
            throw new RuntimeException("smth");
            //throw new NotFoundException(); todo
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Optional<Category> foundedCategory = this.categoryRepository.findByCategory(categoryName);
        if(foundedCategory.isPresent()){
            return foundedCategory.get();
        }else {
            throw new RuntimeException("smth");
            //throw new NotFoundException(); todo
        }
    }

    @Override
    public Category updateCategoryName(long id, String newCategoryName) {
        Category foundedCategory = this.getCategoryById(id);
        foundedCategory.setCategory(newCategoryName);
        return this.saveCategory(foundedCategory);
    }

    @Override
    public void deleteCategory(long id) {
        this.getCategoryById(id);
        this.categoryRepository.deleteById(id);
    }
}