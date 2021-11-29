package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportDao {
    Optional<Report> findById(Long id);

    List<Report> findAll();

    void save(Report report);

    void delete(Report report);
}
