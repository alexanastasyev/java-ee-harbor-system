package ru.rsreu.harbor.controller.command.captain.main;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class ShowCaptainMainPageCommand implements ActionCommand {
    private final ShowCaptainMainPageCommandLogic showCaptainMainPageCommandLogic;

    public ShowCaptainMainPageCommand(ShowCaptainMainPageCommandLogic showCaptainMainPageCommandLogic) {
        this.showCaptainMainPageCommandLogic = showCaptainMainPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.setAttribute(
                Resourcer.getString("request.showCaptainMainPage.attribute.pierAssignment"),
                this.showCaptainMainPageCommandLogic.getPierAssignmentByCaptain(
                        request.getSession().getAttribute(
                                Resourcer.getString("session.attribute.name.user")
                        ).toString())
        );
        return new ActionCommandResult(
                Resourcer.getString("path.page.captainMainPage"),
                ActionCommandResultTypes.FORWARD
        );
    }
}
