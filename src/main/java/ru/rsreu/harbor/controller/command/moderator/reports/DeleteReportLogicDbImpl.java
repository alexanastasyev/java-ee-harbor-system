package ru.rsreu.harbor.controller.command.moderator.reports;

import ru.rsreu.harbor.datalayer.dao.ReportDao;

public class DeleteReportLogicDbImpl implements DeleteReportLogic {
    private final ReportDao reportDao;

    public DeleteReportLogicDbImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public void deleteReport(String id) {
        reportDao.deleteById(this.reportDao.findById(Long.valueOf(id)));
    }
}
