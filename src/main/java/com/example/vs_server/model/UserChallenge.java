package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class UserChallenge {
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("promise_id")
    private Long promiseId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("start_date")
    private Timestamp startDate;
    @JsonProperty("days_number")
    private int daysNumber;

    public UserChallenge() {
    }

    public UserChallenge(Long id, Long userId, Long promiseId, Timestamp startDate, int daysNumber) {
        this.id = id;
        this.userId = userId;
        this.promiseId = promiseId;
        this.startDate = startDate;
        this.daysNumber = daysNumber;
    }

    public UserChallenge(Long userId, Long promiseId, Timestamp startDate, int daysNumber) {
        this.userId = userId;
        this.promiseId = promiseId;
        this.startDate = startDate;
        this.daysNumber = daysNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setPromiseId(Long promiseId) {
        this.promiseId = promiseId;
    }

    public Long getPromiseId() {
        return promiseId;
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