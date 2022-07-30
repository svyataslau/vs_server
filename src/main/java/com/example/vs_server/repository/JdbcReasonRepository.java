package com.example.vs_server.repository;

import com.example.vs_server.model.Reason;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcReasonRepository implements ReasonRepository {

  public JdbcTemplate jdbcTemplate;

  JdbcReasonRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(Reason reason) {
    return jdbcTemplate.update("INSERT INTO reason (user_challenge_id, description) VALUES(?,?)",
            new Object[] { reason.getUserChallengeId(), reason.getDescription() });
  }

  @Override
  public int update(Reason reason) {
    return jdbcTemplate.update("UPDATE reason SET user_challenge_id=?, description=? WHERE id=?",
        new Object[] { reason.getUserChallengeId(), reason.getDescription(), reason.getId() });
  }

  @Override
  public Reason findById(Long id) {
    try {
      Reason reason = jdbcTemplate.queryForObject("SELECT * FROM reason WHERE id=?",
          BeanPropertyRowMapper.newInstance(Reason.class), id);

      return reason;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM reason WHERE id=?", id);
  }

  @Override
  public List<Reason> findAll() {
    return jdbcTemplate.query("SELECT * from reason", BeanPropertyRowMapper.newInstance(Reason.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from reason");
  }
}
