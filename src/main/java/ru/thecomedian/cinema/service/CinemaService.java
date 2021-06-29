package ru.thecomedian.cinema.service;

import io.agroal.api.AgroalDataSource;
import ru.thecomedian.cinema.pojo.Cinema;
import ru.thecomedian.cinema.pojo.Hall;
import ru.thecomedian.cinema.pojo.Place;
import ru.thecomedian.cinema.service.jdbc.JDBCService;
import ru.thecomedian.cinema.utils.ParamGetter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, реализуюющий бизнес-логику программы
 */
@ApplicationScoped
public class CinemaService {

    @Inject
    AgroalDataSource agroalDataSource;

    @Inject
    JDBCService jdbcService;

    /**
     * Получить список кинотеатров
     */
    public List<Cinema> loadCinemaList() throws Exception {
        List<Cinema> result = new ArrayList<>();
        String sqlQuery = "select * from cinema";
        List<Map<String, Object>> queryResult = jdbcService.executeSQL(sqlQuery, Collections.emptyMap());
        if (queryResult != null && !queryResult.isEmpty()) {
            queryResult.forEach(qr -> {
                result.add(new Cinema(qr));
            });
        }
        return result;
    }

    /**
     * Получить список залов для кинотеатра
     */
    public List<Hall> loadHallList(Long cinemaId) throws Exception {
        List<Hall> result = new ArrayList<>();
        String sqlQuery = "select * from hall where cinema_id = :cinemaId ";
        Map<String, Object> params = new HashMap<>();
        params.put("cinemaId", cinemaId);
        List<Map<String, Object>> queryResult = jdbcService.executeSQL(sqlQuery, params);
        if (queryResult != null && !queryResult.isEmpty()) {
            queryResult.forEach(qr -> {
                result.add(new Hall(qr));
            });
        }
        return result;
    }

    /**
     * Получить список мест для зала
     */
    public List<Place> loadPlaceList(Long hallId) throws Exception {
        List<Place> result = new ArrayList<>();
        String sqlQuery = "select * from place where hall_id = :hallId ";
        Map<String, Object> params = new HashMap<>();
        params.put("hallId", hallId);
        List<Map<String, Object>> queryResult = jdbcService.executeSQL(sqlQuery, params);
        if (queryResult != null && !queryResult.isEmpty()) {
            queryResult.forEach(qr -> {
                result.add(new Place(qr));
            });
        }
        return result;
    }

    /**
     * Зарезервировать места
     */
    public String reservePlaces(List<Long> placeIdList) throws Exception {
        String result = "success";
        String sqlQuery = String.format("select * from place where id in (%s)",
                placeIdList.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(",")));
        List<Map<String, Object>> queryResult = jdbcService.executeSQL(sqlQuery, Collections.emptyMap());
        Connection connection = agroalDataSource.getConnection();
        connection.setAutoCommit(false);
        // state определяет последнее состояние места для оптимистической блокировки
        String updateQuery = "update place set reserved = 1, state = :newState where id = :id and state = :state and reserved = 0";
        Map<String, Object> params = new HashMap<>();
        if (queryResult != null && !queryResult.isEmpty()) {
            for (Map<String, Object> qr : queryResult) {
                int state = ParamGetter.getIntegerParam(qr.get("state"));
                // при обновлении состояния места задаем новый state
                params.put("id", qr.get("id"));
                params.put("state", state);
                params.put("newState", state + 1);
                int updatedRows = jdbcService.executeUpdate(updateQuery, params, connection);
                if (updatedRows == 0) {
                    result = "fail";
                    break;
                }
            }
        }
        if ("fail".equals(result)) {
            connection.rollback();
        } else {
            connection.commit();
        }
        connection.close();
        return result;
    }

}
