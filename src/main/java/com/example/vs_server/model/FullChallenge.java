package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class FullChallenge {
    private final Long id;
    @JsonProperty("userId")
    private final Long userId;
    @JsonProperty("promiseId")
    private final Long promiseId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("startDate")
    private final OffsetDateTime startDate;
    @JsonProperty("daysNumber")
    private final int daysNumber;
    private final String title;
    private final String description;
}
