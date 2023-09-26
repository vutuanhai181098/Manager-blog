package com.example.managerblog.service;

import com.example.managerblog.entities.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    Page<Blog> getBlogs(Integer page, Integer pageSize);

    List<Blog> searchBlog(String title);

    Blog getDetailBlog(String slug);

    List<Blog> getBlogsByCategoryName(String name);

}
