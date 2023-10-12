package com.example.managerblog.service.impl;

import com.example.managerblog.dto.UserDto;
import com.example.managerblog.entities.User;
import com.example.managerblog.exception.InvalidCredentialsException;
import com.example.managerblog.mapper.UserMapper;
import com.example.managerblog.repositories.UserRepository;
import com.example.managerblog.request.LoginRequest;
import com.example.managerblog.service.UserService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto checkLoginAdmin(LoginRequest request) {
        // Role: admin ID = 1;
        List<User> adminList = userRepository.findByRoles_Id(1L);
        User user = adminList
                .stream()
                .filter(u -> u.getEmail().equals(request.getEmail()) && u.getPassword().equals(request.getPassword()))
                .findFirst()
                .orElseThrow(() -> new InvalidCredentialsException("Email or password is incorrect!"));
        return userMapper.mapToDto(user);
    }

    @Override
    public long countMember() {
        return userRepository.count();
    }

    @Override
    public Page<UserDto> getAllUser(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id"));
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDto> userDtos = userPage.getContent().stream().map(userMapper::mapToDto).toList();
        return new PageImpl<>(userDtos, pageable, userPage.getTotalElements());
    }
}
