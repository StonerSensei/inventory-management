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

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice); // product by range

    List<Product> findByCategoryIdAndPriceBetween(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice); // product in category range
}
