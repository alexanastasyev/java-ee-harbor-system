package ru.rsreu.harbor.controller.filter.login;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.FrontController;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.command.ActionCommandsDefiner;
import ru.rsreu.harbor.controller.command.EmptyCommand;
import ru.rsreu.harbor.controller.command.login.LoginCommand;
import ru.rsreu.harbor.controller.command.login.ShowLoginPageCommand;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ActionCommand currentCommand = ActionCommandsDefiner.defineCommand(request,
                FrontController.getActionCommandFactoryFromServletContext(request.getServletContext()));

        if (allowExecution(currentCommand, request.getSession())) {
            chain.doFilter(request, response);
        } else {
            request.getServletContext()
                    .getRequestDispatcher(Resourcer.getString("command.path.showMainPage"))
                    .forward(request, response);
        }
    }

    private boolean allowExecution(ActionCommand currentCommand, HttpSession session) {
        return !(currentCommand instanceof ShowLoginPageCommand
                || currentCommand instanceof LoginCommand
                || currentCommand instanceof EmptyCommand)
                || session.getAttribute(Resourcer.getString("session.attribute.name.user")) == null;
    }
}
