package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Pier;

import java.util.List;

public interface PierDao {
    Pier findById(Long id);

    List<Pier> findAll();

    void save();

    void delete(Pier pier);
}
