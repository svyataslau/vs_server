package com.example.vs_server.converter;

import com.example.vs_server.model.FullChallenge;
import com.example.vs_server.model.FullChallengeDto;
import org.springframework.stereotype.Component;

@Component
public class FullChallengeConverter extends Converter<FullChallengeDto, FullChallenge> {

    public FullChallengeConverter() {
        super(FullChallengeConverter::convertToEntity);
    }

    public static FullChallenge convertToEntity(FullChallengeDto fullChallengeDto) {
        return new FullChallenge(fullChallengeDto.getId(), fullChallengeDto.getUserId(), fullChallengeDto.getPromiseId(), fullChallengeDto.getStartDate(), fullChallengeDto.getDaysNumber(), fullChallengeDto.getTitle(), fullChallengeDto.getDescription());
    }

    public static FullChallengeDto convertToDto(FullChallenge fullChallenge) {
        return new FullChallengeDto(fullChallenge.getId(), fullChallenge.getUserId(), fullChallenge.getPromiseId(), fullChallenge.getStartDate(), fullChallenge.getDaysNumber(), fullChallenge.getTitle(), fullChallenge.getDescription());
    }

}
