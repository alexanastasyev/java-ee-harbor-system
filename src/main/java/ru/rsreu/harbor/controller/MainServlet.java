package ru.rsreu.harbor.controller;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.*;
import ru.rsreu.harbor.controller.exception.ActionCommandException;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultArguments;
import ru.rsreu.harbor.datalayer.DaoFactory;
import ru.rsreu.harbor.datalayer.DbType;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.configuration.ServerDbConfiguration;
import ru.rsreu.harbor.datalayer.model.Role;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {
    private DaoFactory daoFactory;
    private ActionCommandsFactory commandsFactory;

    public static ActionCommandsFactory getActionCommandFactoryFromServletContext(ServletContext context) {
        return (ActionCommandsFactory) context.getAttribute(
                Resourcer.getString("servlet.context.attribute.name.commandsFactory"));
    }

    @SuppressWarnings("unchecked")
    public static List<Role> getAllRolesFromServletContext(ServletContext context) {
        return (List<Role>) context.getAttribute(
                Resourcer.getString("servlet.context.attribute.name.allRoles"));
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        DbConfiguration dbConfiguration = new ServerDbConfiguration(
                Resourcer.getString("jdbc.driver.url"),
                Resourcer.getString("jdbc.driver.user"),
                Resourcer.getString("jdbc.driver.password"));
        try {
            this.daoFactory = DaoFactory.getInstance(DbType.ORACLE, dbConfiguration);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.commandsFactory = new ActionCommandFactoryDbImpl(daoFactory);
        this.initServletContext();
    }

    public void destroy() {
        try {
            this.daoFactory.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        this.processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand actionCommand = ActionCommandsDefiner.defineCommand(request, this.commandsFactory);
        ActionCommandResult result;
        try {
            result = actionCommand.execute(request);
        } catch (ActionCommandException exception) {
            result = exception.getActionCommandResult(request);
        }
        ActionCommandResultArguments arguments = new ActionCommandResultArguments(
                request,
                response,
                getServletContext().getRequestDispatcher(result.getPage()),
                result.getPage());
        result.getActionCommandResultType().executeAction(arguments);
    }

    private void initServletContext() {
        getServletContext().setAttribute(Resourcer.getString("servlet.context.attribute.name.allRoles"),
                this.daoFactory.getRoleDao().findAll());
        getServletContext().setAttribute(Resourcer.getString("servlet.context.attribute.name.commandsFactory"),
                this.commandsFactory);
        getServletContext().setAttribute(Resourcer.getString("servlet.context.attribute.name.activeStatus"),
                this.daoFactory.getStatusDao().findByTitle(Resourcer.getString("db.status.active")).orElse(null));
    }
}