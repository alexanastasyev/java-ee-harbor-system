package ru.rsreu.harbor.datalayer.jdbc;

public interface ObjectMapper<T> {
    String[] mapObject(T object);
}
