package ru.rsreu.harbor.controller.command.moderator.reports;

import ru.rsreu.harbor.datalayer.dao.ReportDao;
import ru.rsreu.harbor.datalayer.model.Report;

public class ShowReportPageLogicDbImpl implements ShowReportPageLogic {
    private final ReportDao reportDao;

    public ShowReportPageLogicDbImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public Report getReportById(String id) {
        return reportDao.findById(Long.valueOf(id));
    }
}
