package ru.rsreu.harbor.controller.command.report_system.create;

import ru.rsreu.harbor.datalayer.model.Report;

public interface CreateReportCommandLogic {
    void createReport(Report report);
}
