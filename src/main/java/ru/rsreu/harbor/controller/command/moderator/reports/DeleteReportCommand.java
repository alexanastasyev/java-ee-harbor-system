package ru.rsreu.harbor.controller.command.moderator.reports;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

public class DeleteReportCommand implements ActionCommand {
    private final DeleteReportLogic deleteReportLogic;

    public DeleteReportCommand(DeleteReportLogic deleteReportLogic) {
        this.deleteReportLogic = deleteReportLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        deleteReportLogic.deleteReport(
                request.getParameter(Resourcer.getString("request.deleteReport.attribute.id"))
        );
        return new ActionCommandResult(
                Resourcer.getString("command.path.showModeratorReportsPage"),
                ActionCommandResultTypes.SEND_REDIRECT);
    }
}
