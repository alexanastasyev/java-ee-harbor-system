package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Status;

import java.util.List;

public interface StatusDao {
    Status findById(Long id);

    Status findByTitle(String title);

    List<Status> findAll();
}
