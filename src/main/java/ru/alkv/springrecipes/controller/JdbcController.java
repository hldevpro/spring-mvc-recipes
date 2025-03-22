package ru.alkv.springrecipes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao.PlainSingerDao;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import java.sql.Date;
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

        log.info("Jdbc controller completed request");

        result += "</p>";


        return result;
    }

    private String printAllSingers(PlainSingerDao dao) {
        String result = "<p>All singers:</p>";
        List<Singer> singers = dao.findAll();

        for(Singer singer: singers) {
            result += "<p>";

            result += "Name: " + singer.getFirstName() + " Second name: " + singer.getLastName() + " birth: " + singer.getBirthDate();

            result += "</p>";
        }

        return result;
    }
}
