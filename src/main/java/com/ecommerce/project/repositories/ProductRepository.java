package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryOrderByPriceAsc(Category category);     // just by declaring this particular method, JPA is going to return the implementation of it, just by seein the method name accordingly

    List<Product> findByProductNameLikeIgnoreCase(String keyword);
    // like stands for pattern matching here
}
