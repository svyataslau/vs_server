package com.example.vs_server.repository;

import com.example.vs_server.model.PromiseDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PromiseDaoImpl implements PromiseDao {

    public final JdbcTemplate jdbcTemplate;

    public List<PromiseDto> findAll() {
        return jdbcTemplate.query("SELECT * from promise", BeanPropertyRowMapper.newInstance(PromiseDto.class));
    }
}
