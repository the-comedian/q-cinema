package ru.thecomedian.cinema.pojo;

import ru.thecomedian.cinema.utils.ParamGetter;

import java.util.Map;

/**
 * Класс для зала кинотеатра
 */
public class Hall {

    /**
     * Ид
     */
    private Long id;
    /**
     * Наименование
     */
    private String name;
    /**
     * Ид кинотеатра
     */
    private Long cinemaId;

    public Hall() {
    }

    public Hall(Map<String, Object> map) {
        this.id = ParamGetter.getLongParam(map.get("id"));
        this.name = ParamGetter.getStringParam(map.get("name"));
        this.cinemaId = ParamGetter.getLongParam(map.get("cinema_id"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }
}
