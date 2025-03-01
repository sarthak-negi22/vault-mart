package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service            // tells spring that it needs to manage this class as a bean
public class CategoryServiceImplementation implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    private long nextId = 1L;       // L specifies that its a long literal, not to be confused with int

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

}
