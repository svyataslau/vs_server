package com.example.vs_server.repository;

import com.example.vs_server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(User user) {
    final String SQL_INSERT_QUERY = "INSERT INTO user_profile (nickname, email) VALUES(?,?)";

    PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
            SQL_INSERT_QUERY, Types.VARCHAR, Types.VARCHAR
    );
    pscf.setReturnGeneratedKeys(true);
    pscf.setGeneratedKeysColumnNames("id");

    PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
            Arrays.asList(user.getNickname(), user.getEmail())
    );

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(psc, keyHolder);

    int id = (int) keyHolder.getKey();
    return id;
  }

  @Override
  public int update(User user) {
    return jdbcTemplate.update("UPDATE user_profile SET nickname=?, email=? WHERE id=?",
        new Object[] { user.getNickname(), user.getEmail(), user.getId() });
  }

  @Override
  public User findById(Long id) {
    try {
      User user = jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE id=?",
          BeanPropertyRowMapper.newInstance(User.class), id);

      return user;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM user_profile WHERE id=?", id);
  }

  @Override
  public List<User> findAll() {
    return jdbcTemplate.query("SELECT * from user_profile", BeanPropertyRowMapper.newInstance(User.class));
  }

  @Override
  public User findByEmail(String email) {
    try {
      User user = jdbcTemplate.queryForObject("SELECT * FROM user_profile WHERE email=?", BeanPropertyRowMapper.newInstance(User.class), email);
      return user;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from user_profile");
  }
}
