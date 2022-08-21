package com.example.vs_server.repository;

import com.example.vs_server.model.UserChallengeDto;
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
public class UserChallengeDaoImpl implements UserChallengeDao {

    public final JdbcTemplate jdbcTemplate;

    public UserChallengeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserChallengeDto save(UserChallengeDto userChallengeDto) {
        final String SQL_INSERT_QUERY = "INSERT INTO user_challenge (user_id, promise_id, start_date, days_number) VALUES(?,?,?,?)";

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                SQL_INSERT_QUERY, Types.NUMERIC, Types.NUMERIC, Types.TIMESTAMP, Types.NUMERIC
        );
        pscf.setReturnGeneratedKeys(true);
        pscf.setGeneratedKeysColumnNames("id");

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(userChallengeDto.getUserId(), userChallengeDto.getPromiseId(), userChallengeDto.getStartDate(), userChallengeDto.getDaysNumber())
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        int id = (int) keyHolder.getKey();

        return jdbcTemplate.queryForObject("SELECT * FROM user_challenge WHERE id=?",
                BeanPropertyRowMapper.newInstance(UserChallengeDto.class), id);
    }

    public List<UserChallengeDto> findAll() {
        return jdbcTemplate.query("SELECT * from user_challenge", BeanPropertyRowMapper.newInstance(UserChallengeDto.class));
    }
}
