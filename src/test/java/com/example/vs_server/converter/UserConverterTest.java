package com.example.vs_server.converter;

import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    @InjectMocks
    UserConverter userConverter;

    @Test
    public void shouldConvertToEntities()
    {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1l);
        userDto1.setNickname("someNick1");
        userDto1.setEmail("helper89@yahoo.com");
        userDto1.setPassword("somePassword8989");

        UserDto userDto2 = new UserDto();
        userDto2.setId(2l);
        userDto2.setNickname("someNick2");
        userDto2.setEmail("helper90@yahoo.com");
        userDto2.setPassword("somePassword9090");

        List <UserDto> usersFromDao = new ArrayList();
        usersFromDao.add(userDto1);
        usersFromDao.add(userDto2);

        List <User> response = userConverter.convertToEntities(usersFromDao);

        assertNotNull(response);
        assertEquals(response.size(), 2);

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
    public void shouldConvertToEntity()
    {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1l);
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
    public void shouldConvertToDto()
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
