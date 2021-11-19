package ru.rsreu.harbor.controller.command.moderator.reports;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

public class ShowModeratorReportsPageCommand implements ActionCommand {

    ShowModeratorReportsPageLogic showModeratorReportsPageLogic;

    public ShowModeratorReportsPageCommand(ShowModeratorReportsPageLogic showModeratorReportsPageLogic) {
        this.showModeratorReportsPageLogic = showModeratorReportsPageLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.getSession().setAttribute(
                Resourcer.getString("request.showModeratorReportsPage.attribute.reports"),
                showModeratorReportsPageLogic.getReports()
        );
        return new ActionCommandResult(
                Resourcer.getString("path.page.moderatorReports"),
                ActionCommandResultTypes.FORWARD);
    }
}
