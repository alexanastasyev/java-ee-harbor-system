package ru.rsreu.harbor.controller.exception;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

public class ShowEditUserPageException extends ActionCommandException {
    @Override
    public ActionCommandResult getActionCommandResult(HttpServletRequest request) {
        request.getSession().setAttribute(
                Resourcer.getString("request.attribute.key.errorMessage"),
                Resourcer.getString("message.error.adminPage.edit.noSuchUser")
        );
        return new ActionCommandResult(
                Resourcer.getString("command.path.showAdminPage"),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }
}
