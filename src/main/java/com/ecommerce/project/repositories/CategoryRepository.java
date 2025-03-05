package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRespsitory parameters require Entity type, primary key type
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
