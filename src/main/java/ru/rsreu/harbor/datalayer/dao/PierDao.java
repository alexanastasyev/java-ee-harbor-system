package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;
import java.util.Optional;

public interface PierDao {
    /**
     * Search Pier by id
     * @param id identifier of pier
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<Pier> findById(Long id);

    /**
     * Search all piers
     * @return List of piers and empty list if there are no piers
     */
    List<Pier> findAll();

    /**
     * Search Pier by User instance
     * @param captain captain on pier
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<Pier> findByUser(User captain);

    /**
     * Save new pier assignment
     */
    void save();

    /**
     * Delete existing pier
     * @param pier deleting pier
     */
    void delete(Pier pier);
}
