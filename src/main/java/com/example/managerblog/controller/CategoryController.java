package com.example.managerblog.controller;

import com.example.managerblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Lấy danh sách category.
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    // Lấy danh sách category được sử dụng nhiều nhất.
    @GetMapping("/category/top5")
    public ResponseEntity<?> getTopCategory(){
        return new ResponseEntity<>(categoryService.getTopCategory(), HttpStatus.OK);
    }

    // Lấy danh sách bài viết áp dụng category.
    @GetMapping("/category/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }
}
