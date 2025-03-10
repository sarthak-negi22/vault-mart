package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA repo provides built-im methods for CRUD operations, custom queries.
// JpaRespsitory parameters require Entity type, primary key type
// spring automatically generates SQL queries based on methods name
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);   // spring analyze the method name, and generate the desired SQL corresponding to the method name, without us re-writing the complex SQL
}
