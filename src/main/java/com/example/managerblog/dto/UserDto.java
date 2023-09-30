package com.example.managerblog.dto;

import lombok.*;

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
}
