package ru.rsreu.harbor.controller.command.admin.create;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class ShowCreateUserPageCommand implements ActionCommand {
    private final ShowCreateUserPageLogic showCreateUserPageLogic;

    public ShowCreateUserPageCommand(ShowCreateUserPageLogic showCreateUserPageLogic) {
        this.showCreateUserPageLogic = showCreateUserPageLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.getSession().setAttribute(Resourcer.getString("request.createUserPage.attribute.roles"),
                showCreateUserPageLogic.getAllRoles());
        return new ActionCommandResult(
                Resourcer.getString("path.page.createUser"),
                ActionCommandResultTypes.FORWARD);
    }
}
