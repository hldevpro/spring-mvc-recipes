package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.springframework.jdbc.object.MappingSqlQuery;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllSingers extends MappingSqlQuery<Singer> {
    private final static String SELECT_ALL_SINGERS = "SELECT id, first_name, last_name, birth_date FROM singer";

    public SelectAllSingers(DataSource dataSource) {
        super(dataSource, SELECT_ALL_SINGERS);
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
