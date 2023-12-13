package com.example.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReasonDto {
    private Integer id;
    @JsonProperty("userChallengeId")
    private Integer userChallengeId;
    private String description;
}
