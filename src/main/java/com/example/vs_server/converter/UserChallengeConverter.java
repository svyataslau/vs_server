package com.example.vs_server.converter;

import com.example.vs_server.model.UserChallenge;
import com.example.vs_server.model.UserChallengeDto;
import org.springframework.stereotype.Component;

@Component
public class UserChallengeConverter extends Converter<UserChallengeDto, UserChallenge> {

    public UserChallengeConverter() {
        super(UserChallengeConverter::convertToEntity);
    }

    public static UserChallenge convertToEntity(UserChallengeDto dto) {
        return new UserChallenge(dto.getId(), dto.getUserId(), dto.getPromiseId(), dto.getStartDate(), dto.getDaysNumber());
    }

    public static UserChallengeDto convertToDto(UserChallenge userChallenge) {
        return new UserChallengeDto(userChallenge.getUserId(), userChallenge.getPromiseId(), userChallenge.getStartDate(), userChallenge.getDaysNumber());
    }

}
