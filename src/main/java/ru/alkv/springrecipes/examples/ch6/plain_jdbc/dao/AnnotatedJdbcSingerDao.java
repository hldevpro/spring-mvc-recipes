package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;
import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Album;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("singerDao")
public class AnnotatedJdbcSingerDao implements SingerDao {

    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;

    private SelectAllSingers selectAllSingers;
    private SelectSingerByFirstName selectSingerByFirstName;
    private StoredFunctionFirstNameById storedFunctionFirstNameById;
    private UpdateSinger updateSinger;
    private InsertSinger insertSinger;
    private InsertSingerAlbum insertSingerAlbum;
    private DeleteSinger deleteSinger;

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findAllWithDetail() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql =
                "SELECT s.id, s.first_name, s.last_name, s.birth_date, a.id AS album_id, a.title, a.release_date FROM singer s " +
                "LEFT JOIN album a ON s.id = a.singer_id";

        return jdbcTemplate.query(sql, rs -> {
            Map<Long, Singer> map = new HashMap<>();

            Singer singer = null;

            while (rs.next()) {
                Long id = rs.getLong("id");
                singer = map.get(id);

                if (singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }

                Long albumId = rs.getLong("album_id");
                if (albumId > 0) {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date"));
                    singer.getAlbums().add(album);
                }
            }

            return new ArrayList<>(map.values());
        });
    }

    @Override
    public List<Singer> findAllByFirstName(String firstName) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("first_name", firstName);

        return  selectSingerByFirstName.executeByNamedParam(paramsMap);
    }

    @Override
    public String findFirstNameById(Long id) {
        List<String> result = storedFunctionFirstNameById.execute(id);

        return result.get(0);
    }

    @Override
    public String findNameById(Long id) {
        throw new NotImplementedException("findNameById");
    }

    @Override
    public String findLastNameById(Long id) {
        throw new NotImplementedException("findLastNameById");
    }

    @Override
    public void insertWithDetail(Singer singer) {
        insertSingerAlbum = new InsertSingerAlbum(dataSource);

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        insertSinger.updateByNamedParam(paramMap, keyHolder);

        singer.setId(keyHolder.getKey().longValue());

        logger.info("New singer inserted with id: " + singer.getId());

        List<Album> albums = singer.getAlbums();

        if (albums != null) {
            for (Album album : albums) {
                paramMap = new HashMap<>();
                paramMap.put("singer_id", singer.getId());
                paramMap.put("title", album.getTitle());
                paramMap.put("release_date", album.getReleaseDate());
                insertSingerAlbum.updateByNamedParam(paramMap);
            }
        }

        insertSingerAlbum.flush();
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        insertSinger.updateByNamedParam(paramMap, keyHolder);

        singer.setId(keyHolder.getKey().longValue());

        logger.info("New singer inserted with id: " + singer.getId());
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        paramMap.put("id", singer.getId());

        updateSinger.updateByNamedParam(paramMap);

        logger.info("Existing singer updated with id: " + singer.getId());
    }

    @Override
    public void delete(Long singerId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", singerId);

        deleteSinger.updateByNamedParam(paramMap);

        logger.info("Deleting singer with id: " + singerId);
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
        this.storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
        this.deleteSinger = new DeleteSinger(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
