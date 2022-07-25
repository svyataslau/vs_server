package com.example.vs_server.repository;

import com.example.vs_server.model.UserChallenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserChallengeRepository implements UserChallengeRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(UserChallenge userChallenge) {
    return jdbcTemplate.update("INSERT INTO user_challenge (user_id, promise_id, description, start_date, days_number) VALUES(?,?,?,?,?)",
        new Object[] { userChallenge.getUserId(), userChallenge.getPromiseId(), userChallenge.getDescription(), userChallenge.getStartDate(), userChallenge.getDaysNumber()});
  }

  @Override
  public int update(UserChallenge userChallenge) {
    return jdbcTemplate.update("UPDATE user_challenge SET user_id=?, promise_id=?, description=?, start_date=?, days_number=? WHERE id=?",
        new Object[] { userChallenge.getUserId(), userChallenge.getPromiseId(), userChallenge.getDescription(), userChallenge.getStartDate(), userChallenge.getDaysNumber(), userChallenge.getId() });
  }

  @Override
  public UserChallenge findById(Long id) {
    try {
      UserChallenge userChallenge = jdbcTemplate.queryForObject("SELECT * FROM user_challenge WHERE id=?",
          BeanPropertyRowMapper.newInstance(UserChallenge.class), id);

      return userChallenge;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM user_challenge WHERE id=?", id);
  }

  @Override
  public List<UserChallenge> findAll() {
    return jdbcTemplate.query("SELECT * from user_challenge", BeanPropertyRowMapper.newInstance(UserChallenge.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from user_profile");
  }
}
