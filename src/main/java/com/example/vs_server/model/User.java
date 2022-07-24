package com.example.vs_server.model;

public class User {

  private long id;
  private String nickname;
  private String email;

  public User() {}

  public User(long id, String nickname, String email) {
    this.id = id;
    this.nickname = nickname;
    this.email = email;
  }

  public User(String nickname, String email) {
    this.nickname = nickname;
    this.email = email;
  }

  public void setId(long id) {
    this.id = id;
  }
  public long getId() {
    return id;
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

  @Override
  public String toString() {
    return "Promise [id=" + id + ", nickname=" + nickname  + ", email=" + email + "]";
  }

}
