package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

import java.util.List;

public interface PierAssignmentDao {
    PierAssignment findById(Long id);

    List<PierAssignment> findByPier(Pier pier);
}
