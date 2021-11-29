package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;
import ru.rsreu.harbor.datalayer.model.User;

import java.util.List;
import java.util.Optional;

public interface PierAssignmentDao {
    Optional<PierAssignment> findById(Long id);

    List<PierAssignment> findAll();

    List<PierAssignment> findByPier(Pier pier);

    Optional<PierAssignment> findByCaptain(User captain);

    void save(PierAssignment pierAssignment);

    void delete(PierAssignment pierAssignment);

    void update(PierAssignment pierAssignment);
}
