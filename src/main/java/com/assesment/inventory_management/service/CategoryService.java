package com.assesment.inventory_management.service;

import com.assesment.inventory_management.dto.CategoryRequest;
import com.assesment.inventory_management.dto.CategoryResponse;
import com.assesment.inventory_management.exception.CategoryNotFoundException;
import com.assesment.inventory_management.exception.DuplicateNameException;
import com.assesment.inventory_management.model.Category;
import com.assesment.inventory_management.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryResponse createCategory(CategoryRequest requestDTO) {
        if (categoryRepo.existsByName(requestDTO.getName())) {
            throw new DuplicateNameException("Category", requestDTO.getName());
        }

        Category category = new Category();
        category.setName(requestDTO.getName());
        category.setDescription(requestDTO.getDescription());

        Category savedCategory = categoryRepo.save(category);
        return convertToResponseDTO(savedCategory);
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return convertToResponseDTO(category);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        categoryRepo.deleteById(id);
    }

    private CategoryResponse convertToResponseDTO(Category category) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
