package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReasonDto {
    private Long id;
    @JsonProperty("userChallengeId")
    private Long userChallengeId;
    private String description;
}
