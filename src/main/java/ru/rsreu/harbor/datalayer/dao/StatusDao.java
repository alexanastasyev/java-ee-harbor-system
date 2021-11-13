package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Status;

public interface StatusDao {
    Status findById(Long id);
}
