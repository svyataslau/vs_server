package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class FullChallenge {
    private final Long id;
    @JsonProperty("user_id")
    private final Long userId;
    @JsonProperty("promise_id")
    private final Long promiseId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("start_date")
    private final OffsetDateTime startDate;
    @JsonProperty("days_number")
    private final int daysNumber;
    private final String title;
    private final String description;
}
