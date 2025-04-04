package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteSinger extends SqlUpdate {
    private static final String DELETE_SINGERS = "DELETE FROM singer WHERE first_name = :first_name AND last_name = :last_name";

    public DeleteSinger(DataSource dataSource) {
        super(dataSource, DELETE_SINGERS);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
    }
}
