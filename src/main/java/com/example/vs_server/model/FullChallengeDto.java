package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullChallengeDto {
    private Long id;
    @JsonProperty("user_id")
    @NonNull
    private Long userId;
    @JsonProperty("promise_id")
    @NonNull
    private Long promiseId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("start_date")
    @NonNull
    private OffsetDateTime startDate;
    @JsonProperty("days_number")
    @NonNull
    private int daysNumber;
    @NonNull
    private String title;
    @NonNull
    private String description;
}
