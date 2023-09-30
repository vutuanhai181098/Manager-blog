package com.example.managerblog.mapper;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.entities.User;

public interface UserMapper {
    UserDto mapToDto(User user);
}
