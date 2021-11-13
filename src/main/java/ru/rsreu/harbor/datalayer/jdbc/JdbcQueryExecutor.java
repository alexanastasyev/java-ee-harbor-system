package ru.rsreu.harbor.datalayer.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JdbcQueryExecutor {
    private final JdbcClient jdbcClient;

    public JdbcQueryExecutor(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public <T> List<T> executeQuery(RowMapper<T> rowMapper, String sqlQuery, String... args) {
        List<T> result = new ArrayList<>();
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(sqlQuery, args);
            result = queryResult.stream().map(rowMapper::mapRow).collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
