package ru.alkv.springrecipes.examples.ch6.plain_jdbc.entities;

import java.sql.Date;
import java.io.Serializable;

public class Album implements Serializable {
    private Long id;
    private Long singerId;
    private String title;
    private Date releaseDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public Long getSingerId() {
        return this.singerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    @Override
    public String toString() {
        return "Album - Id: " + id + ", Singer id: " + singerId +
               ", Title: " + title + ", Release Date: " + releaseDate;
    }
}
