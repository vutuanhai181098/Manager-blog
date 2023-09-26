package com.example.managerblog.controller;

import com.example.managerblog.dto.CategoryDto;
import com.example.managerblog.entities.Blog;
import com.example.managerblog.service.BlogService;
import com.example.managerblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String getHomePage(Model model){
        List<CategoryDto> categoryDtos = categoryService.getTopCategory();
        Page<Blog> blogPage = blogService.getBlogs(1, 5);
        model.addAttribute("categoryDtos", categoryDtos);
        model.addAttribute("pages", blogPage);
        return "web/index";
    }

    @GetMapping("/pages/{pageNumber}")
    public String getHomePage(Model model, @PathVariable Integer pageNumber){
        List<CategoryDto> categoryDtos = categoryService.getTopCategory();
        Page<Blog> blogPage = blogService.getBlogs(pageNumber, 5);
        model.addAttribute("categoryDtos", categoryDtos);
        model.addAttribute("pages", blogPage);
        return "web/index";
    }

    @GetMapping("/search")
    public String getSearchPage(Model model){
        return "/web/search";
    }

    @GetMapping("/tags")
    public String getTagsPage(Model model){
        List<CategoryDto> categoryDtos = categoryService.getCategoriesWithPublishedBlogs(true);
        model.addAttribute("categoryDtos", categoryDtos);
        return "web/tags";
    }

    @GetMapping("/tags/{name}")
    public String getDetaiTagPage(Model model, @PathVariable String name){
        List<Blog> blogs = blogService.getBlogsByCategoryName(name);
        model.addAttribute("blogs", blogs);
        model.addAttribute("tag", name);
        return "web/tagDetail";
    }

    @GetMapping("/blog/{slug}")
    public String getDetailBlogPage(Model model, @PathVariable String slug){
        Blog blog = blogService.getDetailBlog(slug);
        model.addAttribute("blog", blog);
        return "web/blogDetail";
    }
}
