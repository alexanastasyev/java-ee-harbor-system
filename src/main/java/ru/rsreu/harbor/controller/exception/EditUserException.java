package ru.rsreu.harbor.controller.exception;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;

public class EditUserException extends ActionCommandException {
    private final static String ID_PARAMETER_URL = "&id=";
    
    @Override
    public ActionCommandResult getActionCommandResult(HttpServletRequest request) {
        request.getSession().setAttribute(
                Resourcer.getString("request.attribute.key.errorMessage"),
                Resourcer.getString("message.error.userForm.invalidData")
        );
        return new ActionCommandResult(
                formEditUserPagePath(request),
                ActionCommandResultTypes.SEND_REDIRECT
        );
    }

    private String formEditUserPagePath(HttpServletRequest request) {
        return Resourcer.getString("command.path.showEditUserPage") +
                ID_PARAMETER_URL +
                request.getParameter(Resourcer.getString("request.editUser.parameter.id"));
    }
}
