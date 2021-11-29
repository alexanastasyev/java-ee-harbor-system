package ru.rsreu.harbor.controller.command.report_system.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.controller.validation.HandleUserBlockingOrCreateReportValidator;
import ru.rsreu.harbor.controller.validation.HandleUserBlockingOrCreateReportValidatorDbImpl;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Report;

import javax.servlet.http.HttpServletRequest;

public class CreateReportDataTransferObjectDbImpl implements DataTransferObject<Report> {
    private final UserDao userDao;

    private final HandleUserBlockingOrCreateReportValidator handleUserBlockingOrCreateReportValidator;

    public CreateReportDataTransferObjectDbImpl(UserDao userDao) {
        this.userDao = userDao;
        this.handleUserBlockingOrCreateReportValidator = new HandleUserBlockingOrCreateReportValidatorDbImpl(
                this.userDao
        );
    }

    @Override
    public Report formModel(HttpServletRequest request) {
        String fromUserIdParameter = request.getParameter(
                Resourcer.getString("request.createReportCommand.parameter.fromUserId")
        );
        String toUserIdParameter = request.getParameter(
                Resourcer.getString("request.createReportCommand.parameter.toUserId"));
        String textParameter = request.getParameter(
                Resourcer.getString("request.createReportCommand.parameter.reportText"));
        if (textParameter == null || textParameter.trim().isEmpty()) {
            textParameter = Resourcer.getString("db.report.text.emptyText");
        }
        if (this.handleUserBlockingOrCreateReportValidator.isValidBlockingOrReportCreating(toUserIdParameter)) {
            try {
                return new Report(
                        null,
                        this.userDao.findById(Long.valueOf(fromUserIdParameter)).orElseThrow(IllegalArgumentException::new),
                        this.userDao.findById(Long.valueOf(toUserIdParameter)).orElseThrow(IllegalArgumentException::new),
                        textParameter
                );
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException();
            }
        }
        throw new IllegalArgumentException();
    }
}
