package com.example.managerblog.controller.view;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.request.LoginRequest;
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

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "admin/view/login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processLogin(LoginRequest request, HttpSession session){
        UserDto userDto = userService.checkLoginAdmin(request);
        session.setAttribute("user", userDto);
        return "redirect:/admin";
    }

    @GetMapping("")
    public String getAdminPage(Model model, HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("user");
        model.addAttribute("user", userDto);
        return "admin/view/home-page";
    }
}
