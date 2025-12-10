package com.assesment.inventory_management.repository;

import com.assesment.inventory_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    Optional<Product> findByName(String name);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByPriceBetween(int minPrice, int maxPrice); // product by range

    List<Product> findByCategoryIdAndPriceBetween(Long categoryId, int minPrice, int maxPrice); // product in category range
}
