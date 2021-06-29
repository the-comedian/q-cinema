package ru.thecomedian.cinema.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для формирования SQL-запроса с помощью JDBC
 */
public class NamedPreparedStatement {

    /**
     * Statement для sql- запроса
     */
    private PreparedStatement prepStmt;
    /**
     * Колонки результата sql-з апроса
     */
    private List<String> fields = new ArrayList<>();

    /**
     * Формирование sql-jdbc запроса
     */
    public NamedPreparedStatement(Connection conn, String sql) throws SQLException {
        int pos;
        while ((pos = sql.indexOf(":")) != -1) {
            int end = sql.substring(pos).indexOf(" ");
            if (end == -1)
                end = sql.length();
            else
                end += pos;
            fields.add(sql.substring(pos + 1, end));
            sql = sql.substring(0, pos) + "?" + sql.substring(end);
        }
        prepStmt = conn.prepareStatement(sql);
    }

    /**
     * Выполнить запрос
     */
    public ResultSet executeQuery() throws SQLException {
        return prepStmt.executeQuery();
    }

    /**
     * Выполнить запрос
     */
    public Integer executeUpdate() throws SQLException {
        return prepStmt.executeUpdate();
    }

    /**
     * Закрыть statement
     */
    public void close() throws SQLException {
        prepStmt.close();
    }

    /**
     * Установить параметр
     *
     * @param name  - имя параметра
     * @param value - значение параметра
     */
    public void setObject(String name, Object value) throws SQLException {
        prepStmt.setObject(getIndex(name), value);
    }

    /**
     * Получить индекс параметра
     *
     * @param name - имя параметра
     */
    private int getIndex(String name) {
        return fields.indexOf(name) + 1;
    }
}

