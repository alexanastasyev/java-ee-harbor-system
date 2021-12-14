package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Status;

import java.util.List;
import java.util.Optional;

public interface StatusDao {
    /**
     * Search status by id
     * @param id identifier of status
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<Status> findById(Long id);

    /**
     * Search status by title
     * @param title title of status
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<Status> findByTitle(String title);

    /**
     * Searches for all statuses
     * @return List of Statuses and empty list if there are no statuses
     */
    List<Status> findAll();
}
