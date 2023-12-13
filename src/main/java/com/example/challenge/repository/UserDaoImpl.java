package com.example.challenge.repository;

import com.example.challenge.model.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    public final JdbcTemplate jdbcTemplate;

    public UserDto getUser(UserDto userDto) {
        return jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE email=? AND password =?",
                BeanPropertyRowMapper.newInstance(UserDto.class), userDto.getEmail(), userDto.getPassword());
    }

    public UserDto save(UserDto userDto) {
        jdbcTemplate.update("INSERT INTO user_profile (nickname, email, password) VALUES(?,?,?)",
                new Object[]{userDto.getNickname(), userDto.getEmail(), userDto.getPassword()});

        return jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE email=?",
                BeanPropertyRowMapper.newInstance(UserDto.class), userDto.getEmail());
    }

    public List<UserDto> findAll() {
        return jdbcTemplate.query("SELECT * from user_profile", BeanPropertyRowMapper.newInstance(UserDto.class));
    }

    public UserDto findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE id=?",
                BeanPropertyRowMapper.newInstance(UserDto.class), id);
    }
}
