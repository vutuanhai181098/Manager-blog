package com.example.managerblog.dto;

import com.example.managerblog.entities.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String avatar;

    private Long totalBlogs;
    private List<Role> roles;
}
