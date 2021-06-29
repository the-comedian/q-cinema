package ru.thecomedian.cinema.service.jdbc;

import io.agroal.api.AgroalDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для работы с JDBC
 */
@ApplicationScoped
public class JDBCService {

    private static final Logger logger = LoggerFactory.getLogger(JDBCService.class.getName());

    @Inject
    AgroalDataSource agroalDataSource;

    /**
     * Выполнить sql- запрос
     *
     * @param params - параметры
     * @return резаультат запроса в виде List<Map<String, Object>>
     */
    public List<Map<String, Object>> executeSQL(String query, Map<String, Object> params) throws Exception {
        return executeSQL(query, params, agroalDataSource.getConnection());
    }

    /**
     * Выполнить sql- запрос
     *
     * @param query      - запрос
     * @param params     - параметры
     * @param connection - соединение
     * @return резаультат запроса в виде List<Map<String, Object>>
     */
    public List<Map<String, Object>> executeSQL(String query, Map<String, Object> params, Connection connection) throws Exception {
        NamedPreparedStatement stmt = null;
        connection.setAutoCommit(false);
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            logQuery(query, params);
            logger.info("Creating statement...");
            // создаем запрос
            stmt = new NamedPreparedStatement(connection, query);
            // пораставляем параметры
            if ((params != null) && (!params.isEmpty())) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    stmt.setObject(entry.getKey(), entry.getValue());
                }
            }
            // получаем результат
            ResultSet rs = stmt.executeQuery();
            // мапим результат
            Map<String, Integer> metadata = this.getMetadata(rs);
            while (rs.next()) {
                Map<String, Object> record = this.mapOneRecord(rs, metadata);
                result.add(record);
            }
            connection.commit();
        } catch (Exception se) {
            logger.error(se.getMessage());
            throw se;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
        }
        return result;
    }

    /**
     * Выполнить sql- запрос. Используется, когда необходимо сделать коммит изменений в БД
     *
     * @param query      - запрос
     * @param params     - параметры
     * @param connection - соединение
     * @return резаультат запроса в виде List<Map<String, Object>>
     */
    public Integer executeUpdate(String query, Map<String, Object> params, Connection connection) throws Exception {
        NamedPreparedStatement stmt = null;
        Integer result;
        try {
            logQuery(query, params);
            logger.info("Creating statement...");
            // создаем запрос
            stmt = new NamedPreparedStatement(connection, query);
            // пораставляем параметры
            if ((params != null) && (!params.isEmpty())) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    stmt.setObject(entry.getKey(), entry.getValue());
                }
            }
            // получаем результат
            result = stmt.executeUpdate();
        } catch (Exception se) {
            logger.error(se.getMessage());
            throw se;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                logger.error(se2.getMessage());
            }
        }
        return result;
    }

    /**
     * Вывод запроса в лог
     */
    private void logQuery(String query, Map<String, Object> params) {
        logger.info("calling query: ");
        logger.info(query);
        logger.info("with params: ");
        if (params != null) {
            logger.info(params.toString());
        }
    }

    /**
     * Получить метаданные (имена колонок) из SQL
     */
    private Map<String, Integer> getMetadata(ResultSet rs) throws SQLException {
        Map<String, Integer> result = new HashMap<>();
        for (Integer i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            String columnName = rs.getMetaData().getColumnName(i);
            result.put(columnName, i);
        }
        return result;
    }

    /**
     * Поместить строку результата SQL-запроса в мапу
     */
    private Map<String, Object> mapOneRecord(ResultSet rs, Map<String, Integer> metadata) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : metadata.entrySet()) {
            result.put(entry.getKey(), rs.getObject(entry.getValue()));
        }
        return result;
    }


}
