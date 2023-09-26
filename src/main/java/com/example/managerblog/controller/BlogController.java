package com.example.managerblog.controller;

import com.example.managerblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BlogController {
    @Autowired
    private BlogService blogService;

    // Lấy danh sách blog.
    @GetMapping("/blogs")
    public ResponseEntity<?> getBlogs(@RequestParam(defaultValue = "1", required = false) Integer page,
                                      @RequestParam(defaultValue = "5", required = false) Integer pageSize){
        return new ResponseEntity<>(blogService.getBlogs(page, pageSize), HttpStatus.OK);
    }

    // Tìm kiếm blog.
    @GetMapping("/search")
    public ResponseEntity<?> searchBlog(@RequestParam String title){
        return new ResponseEntity<>(blogService.searchBlog(title), HttpStatus.OK);
    }

    // Lấy chi tiết bài viết.
    @GetMapping("/blogs/{slug}")
    public ResponseEntity<?> getDetailBlog(@PathVariable String slug){
        return new ResponseEntity<>(blogService.getDetailBlog(slug), HttpStatus.OK);
    }
}
