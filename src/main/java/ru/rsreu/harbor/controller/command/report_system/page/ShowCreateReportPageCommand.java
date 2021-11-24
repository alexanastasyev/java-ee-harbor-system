package ru.rsreu.harbor.controller.command.report_system.page;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain", "dispatcher"})
public class ShowCreateReportPageCommand implements ActionCommand {
    private final ShowCreateReportPageCommandLogic showCreateReportPageCommandLogic;

    public ShowCreateReportPageCommand(ShowCreateReportPageCommandLogic showCreateReportPageCommandLogic) {
        this.showCreateReportPageCommandLogic = showCreateReportPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        User fromUser = this.showCreateReportPageCommandLogic.getUserByLogin(
                request.getSession().getAttribute(
                        Resourcer.getString("session.attribute.name.user")).toString()
        );
        request.setAttribute(Resourcer.getString("request.showMakeReportPage.parameter.fromUser"),
                fromUser);
        request.setAttribute(Resourcer.getString("request.showMakeReportPage.parameter.toUsers"),
                this.showCreateReportPageCommandLogic.getToUsersExcludeFromUser(fromUser));
        return new ActionCommandResult(
                Resourcer.getString("path.page.createReportPage"),
                ActionCommandResultTypes.FORWARD
        );
    }
}
