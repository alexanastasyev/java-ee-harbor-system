package ru.rsreu.harbor.controller.command.report_system.create;

import ru.rsreu.harbor.datalayer.dao.ReportDao;
import ru.rsreu.harbor.datalayer.model.Report;

public class CreateReportCommandLogicDbImpl implements CreateReportCommandLogic {
    private final ReportDao reportDao;

    public CreateReportCommandLogicDbImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public void createReport(Report report) {
        this.reportDao.save(report);
    }
}
