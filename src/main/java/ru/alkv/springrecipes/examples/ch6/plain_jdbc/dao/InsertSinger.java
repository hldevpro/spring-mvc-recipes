package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertSinger extends SqlUpdate {
    private static final String INSERT_SINGER = "INSERT INTO singer (first_name, last_name, birth_date) VALUES (:first_name, :last_name, :birth_date)";

    public InsertSinger(DataSource dataSource) {
        super(dataSource, INSERT_SINGER);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.setGeneratedKeysColumnNames("id");

        super.setReturnGeneratedKeys(true);
    }
}
