package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

public class PlainSingerDao implements SingerDao {

    private static Logger log = LoggerFactory.getLogger(PlainSingerDao.class);

    static {
        try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            log.error("Failed to load DB driver: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/prospring5?useSSL=false",
                "prospring5",
                "prospring5");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        }
        catch (SQLException e) {
            log.error("Failed to close connection to the database!", e);
        }
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM singer");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Singer singer = new Singer();

                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));

                result.add(singer);
            }

            statement.close();
        }
        catch (SQLException e) {
            log.error("Failed to execute SELECT statement: ", e);
        }
        finally {
            closeConnection(connection);
        }
        return result;
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
    public String findNameById(Long Id) {
        return "";
    }

    @Override
    public String findLastNameById(Long id) {
        return "";
    }

    @Override
    public String findFirstNameById(Long id) {
        return "";
    }

    @Override
    public void insert(Singer singer) {
        Connection connection = null;
        try {
            connection = getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO Singer(first_name, last_name, birth_date) VALUES(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());

            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                singer.setId(generatedKeys.getLong(1));
            }

            statement.close();
        }
        catch (SQLException e) {
            log.error("Failed to execute INSERT statement: ", e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Singer singer) {
        throw new NotImplementedException("insert");
    }

    @Override
    public void delete(Long singerId) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Singer WHERE id = ?");
            statement.setLong(1, singerId);

            statement.execute();
            statement.close();
        }
        catch (SQLException ex) {
            log.error("Failed to execute DELETE statement: ", ex);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insertWithDetail(Singer singer) {
        throw new NotImplementedException("insertWithDetail");
    }
}
