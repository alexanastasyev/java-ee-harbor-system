package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Report;

import java.util.List;

public interface ReportDao {
    Report findById(Long id);

    List<Report> findAll();

    void deleteById(Report report);
}
