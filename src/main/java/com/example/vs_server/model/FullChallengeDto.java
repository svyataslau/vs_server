package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FullChallengeDto {
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
    private String title;
    private String description;

    public FullChallengeDto(Long userId, Long promiseId, Timestamp startDate, int daysNumber, String title, String description) {
        this.userId = userId;
        this.promiseId = promiseId;
        this.startDate = startDate;
        this.daysNumber = daysNumber;
        this.title = title;
        this.description = description;
    }
}
