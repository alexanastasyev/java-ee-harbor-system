package ru.rsreu.harbor.controller.command.report_system.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Report;

import javax.servlet.http.HttpServletRequest;

public class CreateReportDataTransferObjectDbImpl implements DataTransferObject<Report> {
    private final UserDao userDao;

    public CreateReportDataTransferObjectDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Report formModel(HttpServletRequest request) {
        return new Report(
                null,
                this.userDao.findById(Long.valueOf(request.getParameter(
                        Resourcer.getString("request.createReportCommand.parameter.fromUserId")
                ))),
                this.userDao.findById(Long.valueOf(request.getParameter(
                        Resourcer.getString("request.createReportCommand.parameter.toUserId")
                ))),
                request.getParameter(Resourcer.getString("request.createReportCommand.parameter.reportText"))
        );
    }
}
