package ru.thecomedian.cinema.pojo;

import ru.thecomedian.cinema.utils.ParamGetter;

import java.util.Map;

/**
 * Класс для места в зале кинотеатра
 */
public class Place {

    /**
     * Ид
     */
    private Long id;
    /**
     * Ряд
     */
    private Integer row;
    /**
     * Место
     */
    private Integer place;
    /**
     * Зарезевировано
     */
    private Integer reserved;
    /**
     * Состояние. Нужно для оптимистических блокировок
     */
    private Integer state;
    /**
     * Ид зала
     */
    private Long hallId;

    public Place() {
    }

    public Place(Map<String, Object> map) {
        this.id = ParamGetter.getLongParam(map.get("id"));
        this.row = ParamGetter.getIntegerParam(map.get("row"));
        this.place = ParamGetter.getIntegerParam(map.get("place"));
        this.reserved = ParamGetter.getIntegerParam(map.get("reserved"));
        this.state = ParamGetter.getIntegerParam(map.get("state"));
        this.hallId = ParamGetter.getLongParam(map.get("hall_id"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

}
