package com.example.vs_server.repository;

import com.example.vs_server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(User user) {
    return jdbcTemplate.update("INSERT INTO user_profile (title, description, published) VALUES(?,?,?)",
        new Object[] { user.getNickname()});
  }

  @Override
  public int update(User user) {
    return jdbcTemplate.update("UPDATE user_profile SET title=?, description=?, published=? WHERE id=?",
        new Object[] { user.getNickname(), user.getId() });
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
  public List<User> findByPublished(boolean published) {
    return jdbcTemplate.query("SELECT * from user_profile WHERE published=?",
        BeanPropertyRowMapper.newInstance(User.class), published);
  }

  @Override
  public List<User> findByNicknameContaining(String title) {
    String q = "SELECT * from user_profile WHERE title ILIKE '%" + title + "%'";

    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(User.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from user_profile");
  }
}
