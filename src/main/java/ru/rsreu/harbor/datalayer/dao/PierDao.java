package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Pier;

public interface PierDao {
    Pier findById(Long id);
}
