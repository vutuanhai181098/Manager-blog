package com.example.managerblog.mapper.impl;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.entities.User;
import com.example.managerblog.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto mapToDto(User user) {
        if(user == null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .totalBlogs((long) user.getBlogs().size())
                .roles(user.getRoles())
                .build();
    }
}
