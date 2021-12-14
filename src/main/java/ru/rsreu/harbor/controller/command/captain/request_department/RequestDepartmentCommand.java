package ru.rsreu.harbor.controller.command.captain.request_department;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.CaptainActionException;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"captain"})
public class RequestDepartmentCommand implements ActionCommand {
    private final RequestDepartmentCommandLogic requestDepartmentCommandLogic;

    public RequestDepartmentCommand(RequestDepartmentCommandLogic requestDepartmentCommandLogic) {
        this.requestDepartmentCommandLogic = requestDepartmentCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws CaptainActionException {
        try {
            this.requestDepartmentCommandLogic.requestDepartment(
                    request.getSession().getAttribute(
                            Resourcer.getString("session.attribute.name.user")
                    ).toString()
            );
        } catch (IllegalArgumentException exception) {
            throw new CaptainActionException();
        }
        return new ActionCommandResult(
                Resourcer.getString("command.path.showCaptainMainPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
