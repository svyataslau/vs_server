package com.example.challenge.converter;

import com.example.challenge.model.User;
import com.example.challenge.model.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @InjectMocks
    UserConverter userConverter;

    @Test
    void shouldConvertToEntities()
    {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setNickname("someNick1");
        userDto1.setEmail("helper89@yahoo.com");
        userDto1.setPassword("somePassword8989");

        UserDto userDto2 = new UserDto();
        userDto2.setId(2);
        userDto2.setNickname("someNick2");
        userDto2.setEmail("helper90@yahoo.com");
        userDto2.setPassword("somePassword9090");

        List <User> response = userConverter.convertToEntities(List.of(userDto1, userDto2));

        assertNotNull(response);
        assertThat(response).hasSize(2);

        assertEquals(response.get(0).getId(),userDto1.getId());
        assertEquals(response.get(0).getNickname(), userDto1.getNickname());
        assertEquals(response.get(0).getEmail(), userDto1.getEmail());
        assertEquals(response.get(0).getPassword(), userDto1.getPassword());

        assertEquals(response.get(1).getId(),userDto2.getId());
        assertEquals(response.get(1).getNickname(), userDto2.getNickname());
        assertEquals(response.get(1).getEmail(), userDto2.getEmail());
        assertEquals(response.get(1).getPassword(), userDto2.getPassword());
    }

    @Test
    void shouldConvertToEntity()
    {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setNickname("someNick1");
        userDto1.setEmail("helper89@yahoo.com");
        userDto1.setPassword("somePassword8989");

        User response = userConverter.convertToEntity(userDto1);

        assertNotNull(response);

        assertEquals(response.getId(),userDto1.getId());
        assertEquals(response.getNickname(), userDto1.getNickname());
        assertEquals(response.getEmail(), userDto1.getEmail());
        assertEquals(response.getPassword(), userDto1.getPassword());
    }

    @Test
    void shouldConvertToDto()
    {
        User user = new User(null,"someNick1","helper89@yahoo.com","somePassword8989");

        UserDto response = userConverter.convertToDto(user);

        assertNotNull(response);

        assertEquals(response.getId(), user.getId());
        assertEquals(response.getNickname(), user.getNickname());
        assertEquals(response.getEmail(), user.getEmail());
        assertEquals(response.getPassword(), user.getPassword());
    }

}
