package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;
import java.util.Optional;

public interface PierAssignmentDao {
    /**
     * Search PierAssignment by id
     * @param id identifier of assignment
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<PierAssignment> findById(Long id);

    /**
     * Search all assignments
     * @return List of PierAssignments and empty list if there are no assignments
     */
    List<PierAssignment> findAll();

    /**
     * Search PierAssignment by Pier instance
     * @param pier pier in assignment
     * @return List of PierAssignments and empty list if there are no assignments
     */
    List<PierAssignment> findByPier(Pier pier);

    /**
     * Search PierAssignment by User instance
     * @param captain captain in assignment
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<PierAssignment> findByCaptain(User captain);

    /**
     * Save new pier assignment
     * @param pierAssignment saving assignment
     */
    void save(PierAssignment pierAssignment);

    /**
     * Delete existing pier assignment
     * @param pierAssignment deleting pier assignment
     */
    void delete(PierAssignment pierAssignment);

    /**
     * Update existing pier assignment
     * @param pierAssignment updating pier assignment
     */
    void update(PierAssignment pierAssignment);
}
