package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class UserChallenge {

  private long id;

  @JsonProperty("user_id")
  private long userId;

  @JsonProperty("promise_id")
  private long promiseId;
  private String description;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @JsonProperty("start_date")
  private Timestamp startDate;

  @JsonProperty("days_number")
  private int daysNumber;

  public UserChallenge() {}

  public UserChallenge(long id, long userId, long promiseId, String description, Timestamp startDate, int daysNumber) {
    this.id = id;
    this.userId = userId;
    this.promiseId = promiseId;
    this.description = description;
    this.startDate = startDate;
    this.daysNumber = daysNumber;
  }

  public UserChallenge(long userId, long promiseId, String description, Timestamp startDate, int daysNumber) {
    this.userId = userId;
    this.promiseId = promiseId;
    this.description = description;
    this.startDate = startDate;
    this.daysNumber = daysNumber;
  }

  public void setId(long id) {
    this.id = id;
  }
  public long getId() {
    return id;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
  public long getUserId() {
    return userId;
  }

  public void setPromiseId(long promiseId) {
    this.promiseId = promiseId;
  }
  public long getPromiseId() {
    return promiseId;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public Timestamp getStartDate() {
    return startDate;
  }
  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public int getDaysNumber() {
    return daysNumber;
  }
  public void setDaysNumber(int daysNumber) {
    this.daysNumber = daysNumber;
  }

}
