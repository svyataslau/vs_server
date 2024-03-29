package com.example.challenge.repository;

import com.example.challenge.model.FullChallengeDto;
import com.example.challenge.model.PromiseDto;
import com.example.challenge.model.ReasonDto;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class FullChallengeDao implements ModelDao<FullChallengeDto> {

    public final JdbcTemplate jdbcTemplate;

    @Override
    public List<FullChallengeDto> findAll() {
        return jdbcTemplate.query("SELECT *, promise.title, reason.description from user_challenge INNER JOIN promise ON promise.id=user_challenge.promise_id JOIN reason ON reason.user_challenge_id=user_challenge.id ORDER BY user_challenge.id", BeanPropertyRowMapper.newInstance(FullChallengeDto.class));
    }

    public List<FullChallengeDto> findAllByUserId(int id) {
        return jdbcTemplate.query("SELECT *, promise.title, reason.description from user_challenge INNER JOIN promise ON promise.id=user_challenge.promise_id JOIN reason ON reason.user_challenge_id=user_challenge.id WHERE user_id = ? ORDER BY user_challenge.id", BeanPropertyRowMapper.newInstance(FullChallengeDto.class), id);
    }

    @Override
    public int save(FullChallengeDto fullChallengeDto) {
        final String SQL_INSERT_QUERY = "INSERT INTO user_challenge (user_id, promise_id, start_date, days_number) VALUES(?,?,?,?)";

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                SQL_INSERT_QUERY, Types.NUMERIC, Types.NUMERIC, Types.TIMESTAMP_WITH_TIMEZONE, Types.NUMERIC
        );
        pscf.setReturnGeneratedKeys(true);
        pscf.setGeneratedKeysColumnNames("id");

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(fullChallengeDto.getUserId(), fullChallengeDto.getPromiseId(),
                        fullChallengeDto.getStartDate(), fullChallengeDto.getDaysNumber())
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        int id = (int) keyHolder.getKey();

        jdbcTemplate.update("INSERT INTO reason (user_challenge_id, description) VALUES(?,?)",
                new Object[]{id, fullChallengeDto.getDescription()});

        List<PromiseDto> promiseList = jdbcTemplate.query("SELECT * FROM user_challenge WHERE promise_id = ?",
                BeanPropertyRowMapper.newInstance(PromiseDto.class), fullChallengeDto.getPromiseId());

        return promiseList.size();
    }

    @Override
    public FullChallengeDto updateById(int id, FullChallengeDto fullChallengeDto) {

        final String SQL_INSERT_QUERY = "UPDATE user_challenge SET user_id=?, promise_id=?, start_date=?, days_number=? WHERE id=?";

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                SQL_INSERT_QUERY, Types.NUMERIC, Types.NUMERIC, Types.TIMESTAMP_WITH_TIMEZONE, Types.NUMERIC, Types.NUMERIC
        );

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(fullChallengeDto.getUserId(), fullChallengeDto.getPromiseId(), fullChallengeDto.getStartDate(), fullChallengeDto.getDaysNumber(), fullChallengeDto.getId())
        );

        jdbcTemplate.update(psc);

        ReasonDto reasonDto = jdbcTemplate.queryForObject("SELECT * from reason WHERE user_challenge_id=?",
                BeanPropertyRowMapper.newInstance(ReasonDto.class), id);

        jdbcTemplate.update("UPDATE reason SET user_challenge_id = ?, description = ? WHERE id = ?",
                new Object[]{fullChallengeDto.getId(), fullChallengeDto.getDescription(), reasonDto.getId()});

        return jdbcTemplate.queryForObject("SELECT *, promise.title, reason.description from user_challenge INNER JOIN promise ON promise.id=user_challenge.promise_id JOIN reason ON reason.user_challenge_id=user_challenge.id WHERE user_challenge.id = ?", BeanPropertyRowMapper.newInstance(FullChallengeDto.class), id);
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM user_challenge WHERE id=?", id);
    }

}
