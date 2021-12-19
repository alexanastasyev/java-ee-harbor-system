package ru.rsreu.harbor.controller.command.login;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.exception.LoginFaultException;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private final LoginCommandLogic loginCommandLogic;

    public LoginCommand(LoginCommandLogic loginCommandLogic) {
        this.loginCommandLogic = loginCommandLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) throws LoginFaultException {
        String login = request.getParameter(Resourcer.getString("request.user.login"));
        String password = request.getParameter(Resourcer.getString("request.user.password"));
        User user = this.loginCommandLogic.getUserByLogin(login);
        String page;
        if (loginCommandLogic.checkLogin(user.getPassword(), password)) {
            loginCommandLogic.setUserOnline(login);
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
