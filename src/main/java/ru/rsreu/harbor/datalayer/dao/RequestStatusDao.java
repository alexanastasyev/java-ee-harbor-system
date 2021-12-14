package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.RequestStatus;

import java.util.Optional;

public interface RequestStatusDao {
    /**
     * Search request status by id
     * @param id identifier of status
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<RequestStatus> findById(Long id);

    /**
     * Search status by title
     * @param title title of status
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<RequestStatus> findByTitle(String title);
}
