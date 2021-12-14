package ru.rsreu.harbor.controller.command.dispatcher.approve_department;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.DispatcherActionException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"dispatcher"})
public class ApproveDepartmentRequestCommand implements ActionCommand {
    private final ApproveDepartmentRequestCommandLogic approveDepartmentRequestCommandLogic;

    public ApproveDepartmentRequestCommand(ApproveDepartmentRequestCommandLogic approveDepartmentRequestCommandLogic) {
        this.approveDepartmentRequestCommandLogic = approveDepartmentRequestCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws DispatcherActionException {
        try {
            this.approveDepartmentRequestCommandLogic.approveDepartmentRequest(
                    request.getParameter(Resourcer.getString(
                            "request.approveDepartmentRequestCommand.parameter.pierAssignmentId"))
            );
        } catch (IllegalArgumentException exception) {
            throw new DispatcherActionException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showDispatcherMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
