package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportDao {
    /**
     * Search report by id
     * @param id identifier of report
     * @return an Optional EMPTY const if there is no object with this id
     */
    Optional<Report> findById(Long id);

    /**
     * Searches for all reports
     * @return List of Reports and empty list if there are no reports
     */
    List<Report> findAll();

    /**
     * Save new report
     * @param report saving report
     */
    void save(Report report);

    /**
     * Delete existing report
     * @param report deleting report
     */
    void delete(Report report);
}
