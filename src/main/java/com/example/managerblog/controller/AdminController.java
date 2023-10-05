package com.example.managerblog.controller;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.entities.Blog;
import com.example.managerblog.service.BlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BlogService blogService;

    public AdminController(BlogService blogService) {
        this.blogService = blogService;
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
}
