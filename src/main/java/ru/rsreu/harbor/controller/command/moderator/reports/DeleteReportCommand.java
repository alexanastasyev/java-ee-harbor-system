package ru.rsreu.harbor.controller.command.moderator.reports;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.ReportNotExistsException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"moderator"})
public class DeleteReportCommand implements ActionCommand {
    private final DeleteReportLogic deleteReportLogic;

    public DeleteReportCommand(DeleteReportLogic deleteReportLogic) {
        this.deleteReportLogic = deleteReportLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws ReportNotExistsException {
        try {
            deleteReportLogic.deleteReport(
                    request.getParameter(Resourcer.getString("request.deleteReport.attribute.id")));
        } catch (IllegalArgumentException exception) {
            throw new ReportNotExistsException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showModeratorReportsPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
