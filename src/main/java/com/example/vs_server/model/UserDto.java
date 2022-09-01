package com.example.vs_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
    private String password;
}
