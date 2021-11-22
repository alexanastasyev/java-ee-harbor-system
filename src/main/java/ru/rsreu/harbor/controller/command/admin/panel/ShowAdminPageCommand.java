package ru.rsreu.harbor.controller.command.admin.panel;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"admin"})
public class ShowAdminPageCommand implements ActionCommand {
    private final ShowAdminPageLogic showAdminPageLogic;

    public ShowAdminPageCommand(ShowAdminPageLogic showAdminPageLogic) {
        this.showAdminPageLogic = showAdminPageLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.setAttribute(Resourcer.getString("request.adminPage.attribute.users"),
                showAdminPageLogic.getAllUsers());

        return new ActionCommandResult(
                Resourcer.getString("path.page.admin"),
                ActionCommandResultTypes.FORWARD
        );
    }
}
