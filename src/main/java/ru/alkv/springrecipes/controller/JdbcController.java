package ru.alkv.springrecipes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao.PlainSingerDao;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao.SingerDao;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Album;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import javax.sql.DataSource;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class JdbcController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationContext context;

    @GetMapping("/jdbc")
    public String jdbc() {
        log.info("In the Jdbc controller!");

        String result = "<p>Jdbc ready!";
        result += "</p>";

        PlainSingerDao dao = new PlainSingerDao();
        List<Singer> singers = dao.findAll();

        result += printAllSingers(dao);

        Singer singerSheeran = new Singer();
        singerSheeran.setFirstName("Ed");
        singerSheeran.setLastName("Sheeran");
        singerSheeran.setBirthDate(
                new Date(
                        (new GregorianCalendar(1991, 2, 19)).getTime().getTime()));

        dao.insert(singerSheeran);

        result += "<p>Added new Singer: " + singerSheeran + " </p>";

        result += printAllSingers(dao);

        dao.delete(singerSheeran.getId());

        result += "<p>Deleted singer </p>";

        result += printAllSingers(dao);

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 AS id");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int mockedVal = resultSet.getInt("id");
                    result += "<p>On connection test got result: " + mockedVal + "</p>";
                }
            }
            catch (Exception ex) {
                result += "<p>Exception on getting DataSource: " + ex.getMessage() + "</p>";
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        catch (SQLException ex) {
            result += "<p>Exception on working with DataSource: " + ex.getMessage() + "</p>";
        }

        SingerDao jdbcTemplatDao = context.getBean("singerDao", SingerDao.class);

        String singerName = jdbcTemplatDao.findNameById(1L);
        result += "<p>Found name using named param jdbc template : " + singerName + "</p>";

        result += "<p>List all singers read with RowMapper </p>";
        result += printAllSingers(jdbcTemplatDao);

        result += "<p>List all singers read with ResultSetExtractor </p>";
        result += printAllSingersWithAlbums(jdbcTemplatDao);

        result += "<p>Using AnnotatedJdbcSingerDao</p>";
        SingerDao jdbcAnnnotatedDao = context.getBean("annotatedJdbcSingerDao", SingerDao.class);

        result += "<p>Show singer John info read with Spring Jdbc Wrappers</p>";

        List<Singer> singersWithJohnOnly = jdbcAnnnotatedDao.findAllByFirstName("John");
        if (singersWithJohnOnly.size() == 2) {
            result += "<p>Found following sinsger for name John using annotated Spring Jdbc Wrappers: " +
                    singersWithJohnOnly.get(0).getFirstName() + " " + singersWithJohnOnly.get(0).getLastName() + "</p>";
        }
        else {
            result += "<p color='red'>Failed to find singer with name John using annotated Spring Jdbc Wrappers</p>";
        }

        singerName = jdbcAnnnotatedDao.findFirstNameById(2L);
        result += "<p>Found name using annotated Spring Jdbc Wrappers: " + singerName + "</p>";

        result += "<p>Inserting new singer Al Yankovich without albums with Spring Jdbc Wrappers</p>";

        Singer singer = new Singer();
        singer.setFirstName("Al");
        singer.setLastName("Yankovich");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1991, 1, 17)).getTime().getTime()));
        jdbcAnnnotatedDao.insert(singer);

        result += "<p>List all singers read with Spring Jdbc Wrappers </p>";
        result += printAllSingers(jdbcAnnnotatedDao);

        result += "<p>Inserting new singer BB King with some albums with Spring Jdbc Wrappers</p>";

        Singer singerKing = new Singer();
        singerKing.setFirstName("BB");
        singerKing.setLastName("King");
        singerKing.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date(
                (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singerKing.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new Date(
                (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singerKing.addAlbum(album);

        jdbcAnnnotatedDao.insertWithDetail(singerKing);

        result += "<p>List all singers details read with Spring Jdbc Wrappers</p>";
        result += printAllSingersWithAlbums(jdbcAnnnotatedDao);

        result += "<p>Deleting two last singers with Spring Jdbc Wrappers</p>";

        jdbcAnnnotatedDao.deleteByName("Al", "Yankovich");

        result += "<p>List all remaining singers after deleting with Spring Jdbc Wrappers </p>";
        result += printAllSingers(jdbcAnnnotatedDao);

        log.info("Jdbc controller completed request");

        result += "</p>";

        return result;
    }

    private String printAllSingers(SingerDao dao) {
        String result = "<p>All singers:</p>";
        List<Singer> singers = dao.findAll();

        for(Singer singer: singers) {
            result += "<p>";

            result += "Name: " + singer.getFirstName() + " Second name: " + singer.getLastName() + " birth: " + singer.getBirthDate();

            result += "</p>";
        }

        return result;
    }

    private String printAllSingersWithAlbums(SingerDao dao) {
        final StringBuilder resultBuilder = new StringBuilder("<p>All singers with albums:</p>");

        List<Singer> singers = dao.findAllWithDetail();
        singers.forEach(singer -> {
            resultBuilder.append("<p>");
            resultBuilder.append("Name: ");
            resultBuilder.append(singer.getFirstName());
            resultBuilder.append(" Second name: ");
            resultBuilder.append(singer.getLastName());
            resultBuilder.append(" Birth date: ");
            resultBuilder.append(singer.getBirthDate());
            resultBuilder.append("</p>");

            if (singer.getAlbums() != null) {
                for (Album album: singer.getAlbums()) {
                    resultBuilder.append("<p>");
                    resultBuilder.append("*     Album title: ");
                    resultBuilder.append(album.getTitle());
                    resultBuilder.append(" Release date: ");
                    resultBuilder.append(album.getReleaseDate());
                    resultBuilder.append("</p>");
                }
            }
        });

        return resultBuilder.toString();
    }
}
