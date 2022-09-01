package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
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
}
