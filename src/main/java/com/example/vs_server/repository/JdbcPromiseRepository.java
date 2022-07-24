package com.example.vs_server.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vs_server.model.Promise;

@Repository
public class JdbcPromiseRepository implements PromiseRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(Promise promise) {
    return jdbcTemplate.update("INSERT INTO default_promise (title) VALUES(?)",
        new Object[] { promise.getTitle()});
  }

  @Override
  public int update(Promise promise) {
    return jdbcTemplate.update("UPDATE default_promise SET title=? WHERE id=?",
        new Object[] { promise.getTitle(), promise.getId() });
  }

  @Override
  public Promise findById(Long id) {
    try {
      Promise promise = jdbcTemplate.queryForObject("SELECT * FROM default_promise WHERE id=?",
          BeanPropertyRowMapper.newInstance(Promise.class), id);

      return promise;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM default_promise WHERE id=?", id);
  }

  @Override
  public List<Promise> findAll() {
    return jdbcTemplate.query("SELECT * from default_promise", BeanPropertyRowMapper.newInstance(Promise.class));
  }

  @Override
  public List<Promise> findByTitleContaining(String title) {
    String q = "SELECT * from default_promise WHERE title ILIKE '%" + title + "%'";

    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Promise.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from default_promise");
  }
}
