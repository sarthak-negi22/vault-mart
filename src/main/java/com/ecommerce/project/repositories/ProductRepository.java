package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageDetails);     // just by declaring this particular method, JPA is going to return the implementation of it, just by seeing the method name accordingly

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);
    // like stands for pattern matching here
}
