package com.example.vs_server.model;

public class UserDto {

  private long id;
  private String nickname;
  private String email;
  private String password;

  public UserDto() {
  }

  public UserDto(String nickname, String email, String password) {
    this.nickname = nickname;
    this.email = email;
    this.password = password;
  }

  public UserDto(long id, String nickname, String email, String password) {
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
