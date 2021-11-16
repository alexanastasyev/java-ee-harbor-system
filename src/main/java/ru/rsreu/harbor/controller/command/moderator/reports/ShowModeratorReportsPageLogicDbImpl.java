package ru.rsreu.harbor.controller.command.moderator.reports;

import ru.rsreu.harbor.datalayer.dao.ReportDao;
import ru.rsreu.harbor.datalayer.model.Report;

import java.util.List;

public class ShowModeratorReportsPageLogicDbImpl implements ShowModeratorReportsPageLogic {
    private final ReportDao reportDao;

    public ShowModeratorReportsPageLogicDbImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public List<Report> getReports() {
        return reportDao.findAll();
    }
}
