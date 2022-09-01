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

    @Override
    public int save(PromiseDto promiseDto) {
        return jdbcTemplate.update("INSERT INTO promise (title) VALUES(?)",
                new Object[]{promiseDto.getTitle()});
    }

    @Override
    public PromiseDto updateById(long id, PromiseDto promiseDto) {
        jdbcTemplate.update("UPDATE promise SET title=? WHERE id=?",
                new Object[]{promiseDto.getTitle(), id});

        return jdbcTemplate.queryForObject("SELECT * FROM promise WHERE id = ?",
                BeanPropertyRowMapper.newInstance(PromiseDto.class), id);
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("DELETE FROM promise WHERE id=?", id);
    }
}
