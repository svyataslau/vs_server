package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReasonDto {

    private Long id;
    @JsonProperty("user_challenge_id")
    private Long userChallengeId;
    private String description;

    public ReasonDto(Long userChallengeId, String description) {
        this.userChallengeId = userChallengeId;
        this.description = description;
    }
}
