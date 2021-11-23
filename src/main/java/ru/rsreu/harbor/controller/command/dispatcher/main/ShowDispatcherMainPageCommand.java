package ru.rsreu.harbor.controller.command.dispatcher.main;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"dispatcher"})
public class ShowDispatcherMainPageCommand implements ActionCommand {
    private final ShowDispatcherMainPageCommandLogic showDispatcherMainPageCommandLogic;

    public ShowDispatcherMainPageCommand(ShowDispatcherMainPageCommandLogic showDispatcherMainPageCommandLogic) {
        this.showDispatcherMainPageCommandLogic = showDispatcherMainPageCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.setAttribute(Resourcer.getString("request.showDispatcherMainPageCommand.pierAssignments"),
                this.showDispatcherMainPageCommandLogic.getPierAssignments());
        request.setAttribute(Resourcer.getString("request.showDispatcherMainPageCommand.piersWithAssignments"),
                this.showDispatcherMainPageCommandLogic.getAllPiersWithAssignments());
        return new ActionCommandResult(
                Resourcer.getString("path.page.dispatcherMainPage"),
                ActionCommandResultTypes.FORWARD
        );
    }
}
