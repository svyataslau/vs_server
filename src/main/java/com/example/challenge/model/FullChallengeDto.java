package com.example.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
public class FullChallengeDto {
    private Integer id;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("promiseId")
    private Integer promiseId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("startDate")
    private OffsetDateTime startDate;
    @JsonProperty("daysNumber")
    private int daysNumber;
    private String title;
    private String description;
}
