package ru.rsreu.harbor.controller.command.captain.main;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.PierAssignment;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class ShowCaptainMainPageCommand implements ActionCommand {
    private final ShowCaptainMainPageCommandLogic showCaptainMainPageCommandLogic;

    public ShowCaptainMainPageCommand(ShowCaptainMainPageCommandLogic showCaptainMainPageCommandLogic) {
        this.showCaptainMainPageCommandLogic = showCaptainMainPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        PierAssignment pierAssignment = this.showCaptainMainPageCommandLogic.getPierAssignmentByCaptain(
                request.getSession().getAttribute(
                        Resourcer.getString("session.attribute.name.user")
                ).toString());
        this.formSuccessfulJspParameters(pierAssignment, request);
        return new ActionCommandResult(
                Resourcer.getString("path.page.captainMainPage"),
                ActionCommandResultTypes.FORWARD
        );
    }

    private void formSuccessfulJspParameters(PierAssignment pierAssignment, HttpServletRequest request) {
        request.setAttribute(
                Resourcer.getString("request.showCaptainMainPage.attribute.pierAssignment"),
                pierAssignment
        );
        if (this.showCaptainMainPageCommandLogic.isArrivalRequestPierAssignmentStatus(pierAssignment)) {
            request.setAttribute(
                    Resourcer.getString("request.showCaptainMainPage.attribute.isFreePiers"),
                    this.showCaptainMainPageCommandLogic.isFreePiers()
            );
        }
    }
}
