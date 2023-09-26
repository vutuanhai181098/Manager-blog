package com.example.managerblog.service;

import com.example.managerblog.dto.CategoryDto;
import com.example.managerblog.entities.Blog;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategory();

    List<CategoryDto> getTopCategory();

    List<Blog> getCategoryByName(String name);

    List<CategoryDto> getCategoriesWithPublishedBlogs(Boolean status);
}
