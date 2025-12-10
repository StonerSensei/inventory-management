package com.assesment.inventory_management.service;


import com.assesment.inventory_management.dto.ProductRequest;
import com.assesment.inventory_management.dto.ProductResponse;
import com.assesment.inventory_management.exception.CategoryNotFoundException;
import com.assesment.inventory_management.exception.DuplicateNameException;
import com.assesment.inventory_management.exception.ProductNotFoundException;
import com.assesment.inventory_management.model.Category;
import com.assesment.inventory_management.model.Product;
import com.assesment.inventory_management.repository.CategoryRepo;
import com.assesment.inventory_management.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public ProductResponse createProduct(ProductRequest requestDTO) {
        if (productRepo.existsByName(requestDTO.getName())) {
            throw new DuplicateNameException("Product", requestDTO.getName());
        }

        Category category = categoryRepo.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(requestDTO.getCategoryId()));

        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());
        product.setCategory(category);

        Product savedProduct = productRepo.save(product);
        return convertToResponseDTO(savedProduct);
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return convertToResponseDTO(product);
    }

    public List<ProductResponse> getAllProducts(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products;

        if (categoryId != null && minPrice != null && maxPrice != null) {
            products = productRepo.findByCategoryIdAndPriceBetween(categoryId, minPrice, maxPrice);
        } else if (categoryId != null) {
            products = productRepo.findByCategoryId(categoryId);
        } else if (minPrice != null && maxPrice != null) {
            products = productRepo.findByPriceBetween(minPrice, maxPrice);
        } else {
            products = productRepo.findAll();
        }

        return products.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponse updateProduct(Long id, ProductRequest requestDTO) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (!product.getName().equals(requestDTO.getName()) &&
                productRepo.existsByName(requestDTO.getName())) {
            throw new DuplicateNameException("Product", requestDTO.getName());
        }

        Category category = categoryRepo.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(requestDTO.getCategoryId()));

        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());
        product.setCategory(category);

        Product updatedProduct = productRepo.save(product);
        return convertToResponseDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepo.deleteById(id);
    }

    private ProductResponse convertToResponseDTO(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }
}
