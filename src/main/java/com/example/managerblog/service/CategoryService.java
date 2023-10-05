package com.example.managerblog.service;

import com.example.managerblog.dto.CategoryDto;
import com.example.managerblog.entities.Blog;
import com.example.managerblog.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategory();

    List<CategoryDto> getTopCategory();

    List<Blog> getCategoryByName(String name);

    List<CategoryDto> getCategoriesWithPublishedBlogs(Boolean status);

    CategoryDto createCategory(CategoryRequest request);

    CategoryDto updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);
}
