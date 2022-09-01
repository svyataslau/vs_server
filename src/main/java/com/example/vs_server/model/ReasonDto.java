package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReasonDto {
    private Long id;
    @JsonProperty("user_challenge_id")
    private final Long userChallengeId;
    private final String description;
}
