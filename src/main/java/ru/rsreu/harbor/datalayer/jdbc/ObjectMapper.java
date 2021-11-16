package ru.rsreu.harbor.datalayer.jdbc;

@FunctionalInterface
public interface ObjectMapper<T> {
    String[] mapObject(T object);
}
