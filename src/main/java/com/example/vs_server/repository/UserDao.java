package com.example.vs_server.repository;

import com.example.vs_server.model.UserDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

  public JdbcTemplate jdbcTemplate;

  public UserDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public UserDto getUser(UserDto userServer) {
    return jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE email=? AND password =?",
            BeanPropertyRowMapper.newInstance(UserDto.class), userServer.getEmail(), userServer.getPassword());
  }

}
