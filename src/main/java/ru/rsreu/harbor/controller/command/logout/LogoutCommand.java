package ru.rsreu.harbor.controller.command.logout;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class LogoutCommand implements ActionCommand {
    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ActionCommandResult(
                Resourcer.getString("command.path.showLoginPage"),
                ActionCommandResultTypes.SEND_REDIRECT,
                new HashMap<>());
    }
}
