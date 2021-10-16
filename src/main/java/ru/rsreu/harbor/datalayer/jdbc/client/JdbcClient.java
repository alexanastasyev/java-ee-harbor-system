package ru.rsreu.harbor.datalayer.jdbc.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JdbcClient {
    private Connection connection;

    public JdbcClient(Connection connection) {
        this.connection = connection;
    }

    public List<Map<String, Object>> executeQuery(String sql, String... arguments) throws SQLException {
        return this.parseResultOfQuery(this.getReadyPreparedStatement(sql, arguments));
    }

    private List<Map<String, Object>> parseResultOfQuery(PreparedStatement statement) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            ResultSet resultSet = statement.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                result.add(parseResultSetRowToMap(resultSet, columnCount));
            }
        } catch (SQLException | NullPointerException e) {

        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {

            }
        }

        return result;
    }

    private Map<String, Object> parseResultSetRowToMap(ResultSet resultSet, int columnCount) throws SQLException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (int i = 1; i <= columnCount; i++) {
            resultMap.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
        }
        return resultMap;
    }

    public PreparedStatement getReadyPreparedStatement(String sql, String... arguments) throws SQLException {
        Map<Integer, String> argumentsForStatement = getArgumentsForStatement(arguments);
        PreparedStatement statement = this.connection.prepareStatement(sql);
        setStringForPreparedStatement(statement, argumentsForStatement);
        return statement;
    }

    private static Map<Integer, String> getArgumentsForStatement(String[] arguments) {
        Map<Integer, String> argumentsForStatement = new HashMap<Integer, String>();
        for (int i = 0; i < arguments.length; i++) {
            argumentsForStatement.put(i + 1, arguments[i]);
        }
        return argumentsForStatement;
    }

    private static void setStringForPreparedStatement(PreparedStatement statement, Map<Integer, String> arguments)
            throws SQLException {
        Iterator<Map.Entry<Integer, String>> iterator = arguments.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            statement.setString(next.getKey(), next.getValue());
        }
    }
}