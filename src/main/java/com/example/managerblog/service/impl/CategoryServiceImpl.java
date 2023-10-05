package com.example.managerblog.service.impl;

import com.example.managerblog.dto.CategoryDto;
import com.example.managerblog.entities.Blog;
import com.example.managerblog.entities.Category;
import com.example.managerblog.exception.BadRequestException;
import com.example.managerblog.exception.ResourceNotFoundException;
import com.example.managerblog.mapper.CategoryMapper;
import com.example.managerblog.repositories.CategoryRepository;
import com.example.managerblog.request.CategoryRequest;
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
    public List<CategoryDto> getCategoriesWithPublishedBlogs(Boolean status) {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category: categories
        ) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            List<Blog> blogs = category.getBlogs().stream().filter(blog -> blog.getStatus().equals(status)).toList();
            categoryDto.setUsed((long) blogs.size());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto createCategory(CategoryRequest request) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream().filter(c -> c.getName().equals(request.getName()))
                .findFirst().orElse(null);
        if(category != null){
            throw new BadRequestException("The category name already exists");
        }
        Category newCategory = new Category(request.getName(), new ArrayList<>());
        categoryRepository.save(newCategory);
        return categoryMapper.mapToDto(newCategory);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + id));

        List<Category> categories = categoryRepository.findAll();
        Category category1 = categories.stream().filter(c -> c.getName().equals(request.getName()))
                .findFirst().orElse(null);

        if(category1 != null){
            throw new BadRequestException("The category name already exists");
        }

        category.setName(request.getName());
        categoryRepository.save(category);
        return categoryMapper.mapToDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found category with id = " + id));
        if(!category.getBlogs().isEmpty()){
            throw new BadRequestException("The category cannot be deleted because there are many blogs in use");
        }
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getTopCategory() {
        return getCategoriesWithPublishedBlogs(true).stream()
                .sorted(Comparator.comparing(CategoryDto::getUsed).reversed())
                .limit(5).collect(Collectors.toList());
    }

    @Override
    public List<Blog> getCategoryByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name);
        return category.getBlogs();
    }

}
