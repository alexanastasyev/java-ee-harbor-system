package ru.rsreu.harbor.controller.command.login;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.LoginFaultException;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASSWORD_PARAMETER_NAME = "password";

    private final LoginCommandLogic loginCommandLogic;

    public LoginCommand(LoginCommandLogic loginCommandLogic) {
        this.loginCommandLogic = loginCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws LoginFaultException {
        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        User user = this.loginCommandLogic.getUserByLogin(login);
        String page;
        if (loginCommandLogic.checkLogin(user.getPassword(), password)) {
            page = Resourcer.getString("command.path.showMainPage");
            this.formSuccessfulJspParameters(user, request);
            return new ActionCommandResult(page, ActionCommandResultTypes.FORWARD);
        } else {
            throw new LoginFaultException();
        }
    }

    private void formSuccessfulJspParameters(User user, HttpServletRequest request) {
        request.getSession().setAttribute(
                Resourcer.getString("request.mainPage.attribute.user"), user.getLogin()
        );
        request.getSession().setAttribute(Resourcer.getString("session.attribute.name.role"),
                user.getRole());
        request.getSession().setAttribute(Resourcer.getString("session.attribute.name.status"),
                user.getStatus());
    }
}
