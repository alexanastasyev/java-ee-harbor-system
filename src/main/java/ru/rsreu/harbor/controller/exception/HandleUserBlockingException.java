package ru.rsreu.harbor.controller.exception;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

public class HandleUserBlockingException extends ActionCommandException {
    @Override
    public ActionCommandResult getActionCommandResult(HttpServletRequest request) {
        request.getSession().setAttribute(
                Resourcer.getString("request.attribute.key.errorMessage"),
                Resourcer.getString("message.error.handleUserBlocking.selfBlocking")
        );
        return new ActionCommandResult(
                Resourcer.getString("command.path.showModeratorUsersPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
