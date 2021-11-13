package ru.rsreu.harbor.datalayer.jdbc;

import java.util.Map;

@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(Map<String, Object> row);
}
