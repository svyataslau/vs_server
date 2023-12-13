package com.example.challenge.converter;

import com.example.challenge.model.FullChallenge;
import com.example.challenge.model.FullChallengeDto;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;

@Component
public class FullChallengeConverter implements Converter<FullChallengeDto, FullChallenge> {
    @Override
    public List<FullChallenge> convertToEntities(Collection<FullChallengeDto> fullChallengeDtos) {
        return fullChallengeDtos.stream().map(this::convertToEntity).toList();
    }

    @Override
    public FullChallenge convertToEntity(FullChallengeDto fullChallengeDto) {
        return new FullChallenge(fullChallengeDto.getId(), fullChallengeDto.getUserId(),
                fullChallengeDto.getPromiseId(), fullChallengeDto.getStartDate(), fullChallengeDto.getDaysNumber(),
                fullChallengeDto.getTitle(), fullChallengeDto.getDescription());
    }

    @Override
    public FullChallengeDto convertToDto(FullChallenge fullChallenge) {
        FullChallengeDto fullChallengeDto = new FullChallengeDto();
        fullChallengeDto.setId(fullChallenge.getId());
        fullChallengeDto.setUserId(fullChallenge.getUserId());
        fullChallengeDto.setPromiseId(fullChallenge.getPromiseId());
        fullChallengeDto.setStartDate(fullChallenge.getStartDate());
        fullChallengeDto.setDaysNumber(fullChallenge.getDaysNumber());
        fullChallengeDto.setTitle(fullChallenge.getTitle());
        fullChallengeDto.setDescription(fullChallenge.getDescription());
        return fullChallengeDto;
    }

}
