package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA repo provides built-im methods for CRUD operations, custom queries.
// JpaRespsitory parameters require Entity type, primary key type
// spring automatically generates SQL queries based on methods name
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
