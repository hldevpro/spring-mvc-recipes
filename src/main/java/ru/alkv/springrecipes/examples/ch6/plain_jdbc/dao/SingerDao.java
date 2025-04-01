package ru.alkv.springrecipes.examples.ch6.plain_jdbc.dao;

import ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findAllWithDetail();
    List<Singer> findAllByFirstName(String firstName);

    String findNameById(Long Id);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);

    void insert(Singer singer);
    void update(Singer singer);
    void delete(Long singerId);

    void insertWithDetail(Singer singer);
}