package com.example.managerblog.service.impl;

import com.example.managerblog.dto.CategoryDto;
import com.example.managerblog.entities.Blog;
import com.example.managerblog.entities.Category;
import com.example.managerblog.mapper.CategoryMapper;
import com.example.managerblog.repositories.CategoryRepository;
import com.example.managerblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            return categoryMapper.mapToDto(category);
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoryByBlogs_Status(Boolean status) {
        List<Category> categories = categoryRepository.findByBlogs_Status(status);
        return categories.stream().map(category -> {
            return categoryMapper.mapToDto(category);
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getTopCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category: categories
             ) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            List<Blog> blogs = category.getBlogs().stream().filter(Blog::getStatus).toList();
            categoryDto.setUsed((long) blogs.size());
            categoryDtos.add(categoryDto);
        }
        categoryDtos = categoryDtos.stream()
                .sorted(Comparator.comparing(CategoryDto::getUsed).reversed())
                .limit(5).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public List<Blog> getCategoryByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name);
        return category.getBlogs();
    }

}
