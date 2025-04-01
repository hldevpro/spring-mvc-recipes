package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Non initialized JdbcTemplate!");
        }

        if (namedParameterJdbcTemplate == null) {
            throw new BeanCreationException("Non initialized NamedParameterJdbcTemplate!");
        }
    }

    @Override
    public List<Singer> findAll() {
        String sql = "SELECT id, first_name,  last_name, birth_date FROM singer";

        return namedParameterJdbcTemplate.query(sql, new SingerMapper());
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
    public String findNameById(Long id) {
        String sql = "SELECT first_name || last_name || ' ' FROM singer WHERE id = :singerId";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singerId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
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

    private static final class SingerMapper implements RowMapper<Singer> {

        @Override
        public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Singer singer = new Singer();

            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));

            return singer;
        }
    }
}
