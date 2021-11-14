package ru.rsreu.harbor.controller.command.login;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultTypes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements ActionCommand {

    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASSWORD_PARAMETER_NAME = "password";

    private final LoginLogic loginLogic;

    public LoginCommand(LoginLogic loginLogic) {
        this.loginLogic = loginLogic;
    }

    @Override
    public ActionCommandResult execute(HttpServletRequest request) {
        String page;

        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);

        Map<String, Object> jspParameters = new HashMap<>();
        if (loginLogic.checkLogin(login, password)) {
            jspParameters.put(Resourcer.getString("request.mainPage.attribute.user"), login);
            page = loginLogic.getUserPageCommand(login);
        } else {
            jspParameters.put(Resourcer.getString("request.attribute.errorLoginPassMessage"),
                    Resourcer.getString("message.loginError"));
            page = Resourcer.getString("path.page.login");
        }

        return new ActionCommandResult(page, ActionCommandResultTypes.SEND_REDIRECT, jspParameters);
    }
}
