package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateSinger extends SqlUpdate {
    private static final String UPDATE_SINGER =
            "UPDATE singer SET first_name = :first_name, last_name = :last_name, birth_date = :birth_date WHERE id = :id";

    public UpdateSinger(DataSource dataSource) {
        super(dataSource, UPDATE_SINGER);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
