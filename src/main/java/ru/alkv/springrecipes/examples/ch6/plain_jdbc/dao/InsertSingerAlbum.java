package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

public class InsertSingerAlbum extends BatchSqlUpdate {
    private static final String INSERT_SINGER_ALBUM = "INSERT INTO album (singer_id, title, release_date) VALUES (:singer_id, :title, :release_date)";

    private static final int BATCH_SIZE = 10;

    public InsertSingerAlbum(DataSource dataSource) {
        super(dataSource, INSERT_SINGER_ALBUM);

        declareParameter(new SqlParameter("singer_id", Types.INTEGER));
        declareParameter(new SqlParameter("title", Types.VARCHAR));
        declareParameter(new SqlParameter("release_date", Types.DATE));

        setBatchSize(BATCH_SIZE);
    }
}
