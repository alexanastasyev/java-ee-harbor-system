package ru.rsreu.harbor.controller.filter.status;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.MainServlet;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.command.ActionCommandsDefiner;
import ru.rsreu.harbor.controller.command.EmptyCommand;
import ru.rsreu.harbor.controller.command.login.LoginCommand;
import ru.rsreu.harbor.controller.command.login.ShowLoginPageCommand;
import ru.rsreu.harbor.controller.command.logout.LogoutCommand;
import ru.rsreu.harbor.datalayer.model.Status;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Status userStatus = (Status) request.getSession().getAttribute(
                Resourcer.getString("session.attribute.name.status"));
        Status activeStatus = (Status) request.getServletContext().getAttribute(
                Resourcer.getString("servlet.context.attribute.name.activeStatus"));
        ActionCommand currentCommand = ActionCommandsDefiner.defineCommand(request,
                MainServlet.getActionCommandFactoryFromServletContext(request.getServletContext()));

        if (allowExecution(activeStatus, userStatus, currentCommand)) {
            chain.doFilter(request, response);
        } else {
            request.getServletContext().
                    getRequestDispatcher(Resourcer.getString("command.path.showInactivePage"))
                    .forward(request, response);
        }
    }

    private boolean allowExecution(Status activeStatus, Status userStatus, ActionCommand currentCommand) {
        return isUserStatusActive(activeStatus, userStatus)
                || currentCommand instanceof LoginCommand || currentCommand instanceof LogoutCommand
                || currentCommand instanceof EmptyCommand || currentCommand instanceof ShowLoginPageCommand;
    }

    private boolean isUserStatusActive(Status activeStatus, Status userStatus) {
        return userStatus == null || userStatus.equals(activeStatus);
    }
}
