package ru.rsreu.harbor.controller.filter;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.ActionCommand;
import ru.rsreu.harbor.controller.command.ActionCommandsDefiner;
import ru.rsreu.harbor.controller.command.ActionCommandsFactory;
import ru.rsreu.harbor.datalayer.model.Role;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoleFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ActionCommand currentCommand = defineCurrentCommand(request);
        List<Role> supportedRoles = defineCommandSupportedRoles(request, currentCommand);
        Role userRole = (Role) request.getSession().getAttribute(
                Resourcer.getString("session.attribute.name.role"));

        if (isRoleSupported(supportedRoles, userRole)) {
            request.getServletContext()
                    .getRequestDispatcher(Resourcer.getString("command.path.showMainPage"))
                    .forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Role> defineCommandSupportedRoles(HttpServletRequest request, ActionCommand command) {
        List<Role> allRoles = (List<Role>) request.getServletContext()
                .getAttribute(Resourcer.getString("servlet.context.attribute.name.allRoles"));
        ActionCommandSupportedRolesDefiner actionCommandSupportedRolesDefiner =
                new ActionCommandSupportedRolesDefiner(allRoles);
        return actionCommandSupportedRolesDefiner.getSupportedRoles(command.getClass());
    }

    private ActionCommand defineCurrentCommand(HttpServletRequest request) {
        return ActionCommandsDefiner
                .defineCommand(request, (ActionCommandsFactory) request.getServletContext()
                        .getAttribute(
                                Resourcer.getString("servlet.context.attribute.name.commandsFactory")));
    }

    private boolean isRoleSupported(List<Role> supportedRoles, Role role) {
        return !(supportedRoles.isEmpty() || (supportedRoles.contains(role)));
    }
}
