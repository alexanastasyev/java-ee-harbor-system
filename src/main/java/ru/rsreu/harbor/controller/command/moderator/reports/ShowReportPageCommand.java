package ru.rsreu.harbor.controller.command.moderator.reports;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"moderator"})
public class ShowReportPageCommand implements ActionCommand {
    private final ShowReportPageLogic showReportPageLogic;

    public ShowReportPageCommand(ShowReportPageLogic showReportPageLogic) {
        this.showReportPageLogic = showReportPageLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.setAttribute(
                Resourcer.getString("request.showReportPage.attribute.report"),
                showReportPageLogic.getReportById(
                        request.getParameter(Resourcer.getString("request.showReport.parameter.id"))
                )
        );
        return new ActionCommandResult(
                Resourcer.getString("path.page.report"),
                ActionCommandResultTypes.FORWARD);
    }
}
