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
    return jdbcTemplate.update("INSERT INTO promise (title) VALUES(?)",
        new Object[] { promise.getTitle()});
  }

  @Override
  public int update(Promise promise) {
    return jdbcTemplate.update("UPDATE promise SET title=? WHERE id=?",
        new Object[] { promise.getTitle(), promise.getId() });
  }

  @Override
  public Promise findById(Long id) {
    try {
      Promise promise = jdbcTemplate.queryForObject("SELECT * FROM promise WHERE id=?",
          BeanPropertyRowMapper.newInstance(Promise.class), id);

      return promise;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM promise WHERE id=?", id);
  }

  @Override
  public List<Promise> findAll() {
    return jdbcTemplate.query("SELECT * from promise", BeanPropertyRowMapper.newInstance(Promise.class));
  }

  @Override
  public List<Promise> findByTitleContaining(String title) {
    String q = "SELECT * from promise WHERE title ILIKE '%" + title + "%'";

    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Promise.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from promise");
  }
}
