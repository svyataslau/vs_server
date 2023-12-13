package com.example.challenge.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private final Integer id;
    private final String nickname;
    private final String email;
    private final String password;
    private List<FullChallenge> challenges;
}
