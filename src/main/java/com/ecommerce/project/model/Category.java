package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "categories")     // an entity in JPA means, a table in DB. This particular class will be mapped to a table in DB by spring, and the table will contain attributes of the class members. The name of the entity is "categories"
public class Category {

    @Id     // means this attribute will be the primary key of the table
    private Long categoryId;
    private String categoryName;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
