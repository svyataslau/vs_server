package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reason {

  private long id;
  @JsonProperty("user_challenge_id")
  private long userChallengeId;
  private String description;

  public Reason() {

  }

  public Reason(long id, long userChallengeId, String description) {
    this.id = id;
    this.userChallengeId = userChallengeId;
    this.description = description;
  }

  public Reason(long userChallengeId, String description) {
    this.userChallengeId = userChallengeId;
    this.description = description;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return id;
  }

  public void setUserChallengeId(long userChallengeId) {
    this.userChallengeId = userChallengeId;
  }

  public long getUserChallengeId() {
    return userChallengeId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Reason [id=" + id + ", userChallengeId=" + userChallengeId  + ", description=" + description  + "]";
  }

}
