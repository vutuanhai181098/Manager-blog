package com.example.managerblog.service;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.request.LoginRequest;

public interface UserService {
    UserDto checkLoginAdmin(LoginRequest request);
}
