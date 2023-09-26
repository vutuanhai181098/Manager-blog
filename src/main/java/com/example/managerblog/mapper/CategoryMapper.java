//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.managerblog.mapper;

import com.example.managerblog.dto.CategoryDto;
import com.example.managerblog.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto mapToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .used((long)category.getBlogs().size())
                .build();
    }
}
