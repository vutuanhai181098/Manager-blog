package com.example.managerblog.service.impl;

import com.example.managerblog.entities.Blog;
import com.example.managerblog.exception.ResourceNotFoundException;
import com.example.managerblog.repositories.BlogRepository;
import com.example.managerblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> getBlogs(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        return blogRepository.findByStatusTrue(pageable);
    }

    @Override
    public List<Blog> searchBlog(String title) {
        List<Blog> blogs = blogRepository.findByTitleContainingIgnoreCase(title);
        return blogs.stream().filter(Blog::getStatus).collect(Collectors.toList());
    }

    @Override
    public Blog getDetailBlog(String slug) {
        return blogRepository.findBySlugAndStatusTrue(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Not found blog"));
    }

    @Override
    public List<Blog> getBlogsByCategoryName(String name) {
        return blogRepository.findByCategoryName(name);
    }
}
