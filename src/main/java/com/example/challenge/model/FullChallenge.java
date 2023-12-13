package com.example.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class FullChallenge {
    private final Integer id;
    @JsonProperty("userId")
    private final Integer userId;
    @JsonProperty("promiseId")
    private final Integer promiseId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("startDate")
    private final OffsetDateTime startDate;
    @JsonProperty("daysNumber")
    private final int daysNumber;
    private final String title;
    private final String description;
}
