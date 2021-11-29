package ru.rsreu.harbor.controller.command.report_system.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.controller.exception.CreateReportException;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.Report;

import javax.servlet.http.HttpServletRequest;

public class CreateReportCommand implements ActionCommand {
    private final CreateReportCommandLogic createReportCommandLogic;
    private final DataTransferObject<Report> reportDataTransferObject;

    public CreateReportCommand(
            CreateReportCommandLogic createReportCommandLogic,
            DataTransferObject<Report> reportDataTransferObject) {
        this.createReportCommandLogic = createReportCommandLogic;
        this.reportDataTransferObject = reportDataTransferObject;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws CreateReportException {
        try {
            this.createReportCommandLogic.createReport(this.reportDataTransferObject.formModel(request));
        } catch (IllegalArgumentException exception) {
            throw new CreateReportException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
