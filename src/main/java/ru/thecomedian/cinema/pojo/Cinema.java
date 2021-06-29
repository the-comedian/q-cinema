package ru.thecomedian.cinema.pojo;

import ru.thecomedian.cinema.utils.ParamGetter;

import java.util.Map;

/**
 * Класс для кинотеатра
 */
public class Cinema {

    /**
     * Ид
     */
    private Long id;
    /**
     * Наименование
     */
    private String name;
    /**
     * Сокращение
     */
    private String brief;

    public Cinema() {
    }

    public Cinema(Map<String, Object> map) {
        this.id = ParamGetter.getLongParam(map.get("id"));
        this.name = ParamGetter.getStringParam(map.get("name"));
        this.brief = ParamGetter.getStringParam(map.get("brief"));
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

}
