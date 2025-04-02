package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFunctionFirstNameById extends SqlFunction<String> {
    public static final String EXECUTE_STORED_FUNC = "SELECT getfirstnamebyid(?)";

    public StoredFunctionFirstNameById(DataSource dataSource) {
        super(dataSource, EXECUTE_STORED_FUNC);

        declareParameter(new SqlParameter(Types.INTEGER));

        compile();
    }
}
