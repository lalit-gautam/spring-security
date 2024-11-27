package com.security.repository;

import com.security.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findByName(String name);
    List<Products> findByCategory(String category);
}
