package com.example.vs_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
