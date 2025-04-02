package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SelectSingerByFirstName extends MappingSqlQuery {
    private static final String FIND_BY_FIRST_NAME = "SELECT id, first_name, last_name, birth_date FROM singer WHERE first_name = :first_name";

    public SelectSingerByFirstName(DataSource dataSource) {
        super(dataSource, FIND_BY_FIRST_NAME);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
    }

    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Singer singer = new Singer();

        singer.setId(rs.getLong("id"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));

        return singer;
    }
}
