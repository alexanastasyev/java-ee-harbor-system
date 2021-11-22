package ru.rsreu.harbor.controller.command.admin.pier;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class DeletePierCommand implements ActionCommand {
    private final DeletePierLogic deletePierLogic;

    public DeletePierCommand(DeletePierLogic deletePierLogic) {
        this.deletePierLogic = deletePierLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        this.deletePierLogic.deletePierById(request.getParameter(
                Resourcer.getString("request.deletePierCommand.parameter.id")));
        return new ActionCommandResult(
                Resourcer.getString("command.path.showAdminPiersPage"),
                ActionCommandResultTypes.SEND_REDIRECT);
    }
}
