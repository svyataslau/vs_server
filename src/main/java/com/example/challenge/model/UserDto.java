package com.example.challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    private Integer id;
    private String nickname;
    private String email;
    private String password;
}
