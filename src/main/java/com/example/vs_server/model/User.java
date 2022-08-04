package com.example.vs_server.model;

import io.swagger.annotations.ApiModelProperty;

public class User {

    @ApiModelProperty(notes = "Database id of the User", name = "id")
    private long id;
    @ApiModelProperty(notes = "Nickname of the User", name = "nickname", value = "jonny3248359253")
    private String nickname;
    @ApiModelProperty(notes = "Email of the User", name = "email", required = true, value = "letsgo763@gmail.com")
    private String email;
    @ApiModelProperty(notes = "Password of the User", name = "password", required = true, value = "somePass32")
    private String password;

    public User() {
    }

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public User(long id, String nickname, String email, String password) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
