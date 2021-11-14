package ru.rsreu.harbor.datalayer.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcClient {
    private final Connection connection;

    public JdbcClient(Connection connection) {
        this.connection = connection;
    }

    public List<Map<String, Object>> executeQuery(String sql, String... arguments) throws SQLException {
        return this.parseResultOfQuery(this.getReadyPreparedStatement(sql, arguments));
    }

    private List<Map<String, Object>> parseResultOfQuery(PreparedStatement statement) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();
            if (columnCount > 0) {
                while (resultSet.next()) {
                    result.add(parseResultSetRowToMap(resultSet, columnCount));
                }
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private Map<String, Object> parseResultSetRowToMap(ResultSet resultSet, int columnCount) throws SQLException {
        Map<String, Object> resultMap = new HashMap<>();
        for (int i = 1; i <= columnCount; i++) {
            resultMap.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
        }
        return resultMap;
    }

    public PreparedStatement getReadyPreparedStatement(String sql, String... arguments) throws SQLException {
        Map<Integer, String> argumentsForStatement = getArgumentsForStatement(arguments);
        return getPreparedStatementAsString(sql, argumentsForStatement);
    }

    private static Map<Integer, String> getArgumentsForStatement(String[] arguments) {
        Map<Integer, String> argumentsForStatement = new HashMap<>();
        for (int i = 0; i < arguments.length; i++) {
            argumentsForStatement.put(i + 1, arguments[i]);
        }
        return argumentsForStatement;
    }

    private PreparedStatement getPreparedStatementAsString(String sql, Map<Integer, String> arguments)
            throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(sql);
        for (Map.Entry<Integer, String> next : arguments.entrySet()) {
            statement.setString(next.getKey(), next.getValue());
        }
        return statement;
    }
}