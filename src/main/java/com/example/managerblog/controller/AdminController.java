package com.example.managerblog.controller;

import com.example.managerblog.dto.CategoryDto;
import com.example.managerblog.dto.UserDto;
import com.example.managerblog.entities.Blog;
import com.example.managerblog.service.BlogService;
import com.example.managerblog.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    public AdminController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping("/blogs")
    public String getAllBlog(Model model,@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("user");
        model.addAttribute("user", userDto);
        Page<Blog> blogPage =  blogService.getAllBlog(page, pageSize);
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("currentPage", page);
        return "admin/view/all-blog";
    }

    @GetMapping("/categories")
    public String getCategoryPage(Model model){
        List<CategoryDto> dtoList = categoryService.getAllCategory();
        model.addAttribute("categories", dtoList);
        return "admin/view/categories";
    }
}
