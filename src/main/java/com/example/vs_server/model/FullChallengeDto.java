package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("start_date")
    private OffsetDateTime startDate;
    @JsonProperty("days_number")
    private int daysNumber;
    private String title;
    private String description;

    public FullChallengeDto(Long userId, Long promiseId, OffsetDateTime startDate, int daysNumber, String title, String description) {
        this.userId = userId;
        this.promiseId = promiseId;
        this.startDate = startDate;
        this.daysNumber = daysNumber;
        this.title = title;
        this.description = description;
    }
}
