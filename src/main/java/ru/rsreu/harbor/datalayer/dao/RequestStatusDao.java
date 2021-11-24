package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.RequestStatus;

import java.util.Optional;

public interface RequestStatusDao {
    Optional<RequestStatus> findById(Long id);

    Optional<RequestStatus> findByTitle(String title);
}
