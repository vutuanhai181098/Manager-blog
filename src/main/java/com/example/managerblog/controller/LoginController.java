package com.example.managerblog.controller;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.request.LoginRequest;
import com.example.managerblog.service.BlogService;
import com.example.managerblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class LoginController {

    private final UserService userService;
    private final BlogService blogService;

    public LoginController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "admin/view/login";
    }
//
//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String processLogin(LoginRequest request, HttpSession session){
//        UserDto userDto = userService.checkLoginAdmin(request);
//        session.setAttribute("user", userDto);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("")
//    public String getAdminPage(Model model, HttpSession session){
//        if (session.getAttribute("user") == null){
//            return "redirect:/admin/login";
//        }
//        UserDto userDto = (UserDto) session.getAttribute("user");
//        long blogPublic = blogService.countBlogsByStatus(Boolean.TRUE);
//        long blogPrivate = blogService.countBlogsByStatus(Boolean.FALSE);
//        long countMember = userService.countMember();
//        model.addAttribute("user", userDto);
//        model.addAttribute("blogPublic", blogPublic);
//        model.addAttribute("blogPrivate", blogPrivate);
//        model.addAttribute("countMember", countMember);
//        return "admin/view/home-page";
//    }
}
