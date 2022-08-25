package com.example.vs_server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    @ApiModelProperty(notes = "Database id of the User", name = "id")
    private Long id;
    @ApiModelProperty(notes = "Nickname of the User", name = "nickname", value = "jonny3248359253")
    private String nickname;
    @ApiModelProperty(notes = "Email of the User", name = "email", required = true, value = "letsgo763@gmail.com")
    private String email;
    @ApiModelProperty(notes = "Password of the User", name = "password", required = true, value = "somePass32")
    private String password;
    private List<FullChallenge> challenges;

    public User() {
    }

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String nickname, String email, String password) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

}
