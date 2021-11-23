package ru.rsreu.harbor.controller.command.main;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.Role;

import javax.servlet.http.HttpServletRequest;

public class ShowMainPageCommand implements ActionCommand {
    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        return new ActionCommandResult(
                getPageByRole((Role) request.getSession()
                        .getAttribute(Resourcer.getString("session.attribute.name.role"))),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }

    private static String getPageByRole(Role role) {
        if (role == null) {
            return Resourcer.getString("command.path.showLoginPage");
        }
        String result;
        switch (role.getTitle()) {
            case "admin":
            {
                result = Resourcer.getString("command.path.showAdminPage");
                break;
            }
            case "moderator":
            {
                result = Resourcer.getString("command.path.showModeratorUsersPage");
                break;
            }
            case "captain":
            {
                result = Resourcer.getString("command.path.showCaptainMainPage");
                break;
            }
            case "dispatcher":
            {
                result = Resourcer.getString("command.path.showDispatcherMainPage");
                break;
            }
            default: {
                result = Resourcer.getString("command.path.showLoginPage");
            }
        }

        return result;
    }
}
