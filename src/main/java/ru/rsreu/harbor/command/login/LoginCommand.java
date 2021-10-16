package ru.rsreu.harbor.command.login;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASSWORD_PARAMETER_NAME = "password";

    private final LoginLogic loginLogic;

    public LoginCommand(LoginLogic loginLogic) {
        this.loginLogic = loginLogic;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);

        if (loginLogic.checkLogin(login, password)) {
            request.setAttribute(Resourcer.getString("request.attribute.user"), login);
            page = Resourcer.getString("path.page.main");
        } else {
            request.setAttribute(Resourcer.getString("request.attribute.errorLoginPassMessage"),
                    Resourcer.getString("message.loginError"));
            page = Resourcer.getString("path.page.login");
        }

        return page;
    }
}
