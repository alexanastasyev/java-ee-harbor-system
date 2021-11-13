package ru.rsreu.harbor.datalayer.dao;

import ru.rsreu.harbor.datalayer.model.Report;

public interface ReportDao {
    Report findById(Long id);
}
