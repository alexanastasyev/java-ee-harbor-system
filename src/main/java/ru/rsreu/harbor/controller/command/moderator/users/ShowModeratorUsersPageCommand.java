package ru.rsreu.harbor.controller.command.moderator.users;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.filter.role.CommandSupportedRolesTitles;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

@CommandSupportedRolesTitles(titles = {"moderator"})
public class ShowModeratorUsersPageCommand implements ActionCommand {
    private final ShowModeratorUsersPageLogic showModeratorUsersPageLogic;

    public ShowModeratorUsersPageCommand(ShowModeratorUsersPageLogic showModeratorUsersPageLogic) {
        this.showModeratorUsersPageLogic = showModeratorUsersPageLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.setAttribute(
                Resourcer.getString("request.moderatorUsersPage.attribute.users"),
                showModeratorUsersPageLogic.getUsers()
        );
        return new ActionCommandResult(
                Resourcer.getString("path.page.moderatorUsers"),
                ActionCommandResultTypes.FORWARD
        );
    }
}
