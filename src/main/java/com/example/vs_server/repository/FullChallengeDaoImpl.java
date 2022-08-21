package com.example.vs_server.repository;

import com.example.vs_server.model.FullChallengeDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FullChallengeDaoImpl implements FullChallengeDao {

    public final JdbcTemplate jdbcTemplate;

    public FullChallengeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FullChallengeDto> findAll() {
        return jdbcTemplate.query("SELECT user_challenge.id, user_challenge.user_id, user_challenge.promise_id, user_challenge.start_date, user_challenge.days_number, promise.title, reason.description from user_challenge INNER JOIN promise ON promise.id=user_challenge.promise_id JOIN reason ON reason.user_challenge_id=user_challenge.id", BeanPropertyRowMapper.newInstance(FullChallengeDto.class));
    }

    public List<FullChallengeDto> findAllById(long id) {
        return jdbcTemplate.query("SELECT user_challenge.id, user_challenge.user_id, user_challenge.promise_id, user_challenge.start_date, user_challenge.days_number, promise.title, reason.description from user_challenge INNER JOIN promise ON promise.id=user_challenge.promise_id JOIN reason ON reason.user_challenge_id=user_challenge.id WHERE user_id = ?", BeanPropertyRowMapper.newInstance(FullChallengeDto.class), id);
    }
}
