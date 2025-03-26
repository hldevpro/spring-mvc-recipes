package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import java.util.List;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Non initialized JdbcTemplate!");
        }
    }

    @Override
    public List<Singer> findAll() {
        return List.of();
    }

    @Override
    public List<Singer> findAllWithDetail() {
        return List.of();
    }

    @Override
    public List<Singer> findAllByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public String findLastNameById(Long id) {
        return "";
    }

    @Override
    public String findFirstNameById(Long id) {
        return this.jdbcTemplate.queryForObject(
                "SELECT first_name FROM singer WHERE id = ?",
                new Object[] {id},
                String.class);
    }

    @Override
    public void insert(Singer singer) {
        throw new NotImplementedException("insert");
    }

    @Override
    public void update(Singer singer) {
        throw new NotImplementedException("update");
    }

    @Override
    public void delete(Long singerId) {
        throw new NotImplementedException("delete");
    }

    @Override
    public void insertWithDetail(Singer singer) {
        throw new NotImplementedException("insertWithDetail");
    }
}
