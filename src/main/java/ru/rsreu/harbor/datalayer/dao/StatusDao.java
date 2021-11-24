package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Status;

import java.util.List;
import java.util.Optional;

public interface StatusDao {
    Optional<Status> findById(Long id);

    Optional<Status> findByTitle(String title);

    List<Status> findAll();
}
