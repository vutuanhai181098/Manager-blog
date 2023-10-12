package com.example.managerblog.service;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.request.LoginRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDto checkLoginAdmin(LoginRequest request);

    long countMember();

    Page<UserDto> getAllUser(Integer page, Integer pageSize);
}
