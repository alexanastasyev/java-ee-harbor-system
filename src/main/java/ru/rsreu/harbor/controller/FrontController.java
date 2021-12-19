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

/**
 * Handles all incoming requests. Form answers
 */
public class FrontController extends HttpServlet {
    /**
     * Main dao factory
     */
    private DaoFactory daoFactory;

    /**
     * Main commands factory
     */
    private ActionCommandsFactory commandsFactory;

    /**
     * Method for getting command factory from servlet context
     * @param context the context from which to get the factory
     * @return ActionCommandsFactory instance
     */
    public static ActionCommandsFactory getActionCommandFactoryFromServletContext(ServletContext context) {
        return (ActionCommandsFactory) context.getAttribute(
                Resourcer.getString("servlet.context.attribute.name.commandsFactory"));
    }

    /**
     * Method for getting all system roles from servlet context
     * @param context the context from which to get the roles
     * @return - List of Role instances
     */
    @SuppressWarnings("unchecked")
    public static List<Role> getAllRolesFromServletContext(ServletContext context) {
        return (List<Role>) context.getAttribute(
                Resourcer.getString("servlet.context.attribute.name.allRoles"));
    }

    /**
     *  Called by the servlet container to indicate to a servlet that the
     *  servlet is being placed into service.
     * @param servletConfig the <code>ServletConfig</code> object
     *      					that contains configuration
     *      					information for this servlet
     * @throws ServletException if an exception occurs that
     *      					interrupts the servlet's normal
     *      					operation
     */
    @Override
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

    /**
     * The <code>ServletConfig</code> object
     *      					that contains configuration
     *      					information for this servlet
     */
    public void destroy() {
        try {
            this.daoFactory.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle GET-requests
     * @param request the request object that is passed to the servlet
     * @param response the response object that the servlet uses to return the headers to the client
     * @throws IOException if an input or output error occurs
     * @throws ServletException if the request for the HEAD
     *                                        could not be handled
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.processRequest(request, response);
    }

    /**
     * Handle POST-requests
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the
     * @throws IOException if an input or output error occurs
     *                                    while the servlet is handling the
     *                                    HTTP request
     * @throws ServletException if the HTTP request
     *                                       cannot be handled
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.processRequest(request, response);
    }

    /**
     * Depending on the command that came in the request, it performs some actions related to the command logic. Forms a response
     * @param request request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     * @param response response the {@link HttpServletResponse} object that contains the response the servlet returns to the
     * @throws ServletException if an input or output error occurs while the servlet is handling the HTTP request
     * @throws IOException if the HTTP request cannot be handled
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
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

    /**
     * Writes the values required for the filters to work in the context
     */
    private void initServletContext() {
        getServletContext().setAttribute(Resourcer.getString("servlet.context.attribute.name.allRoles"),
                this.daoFactory.getRoleDao().findAll());
        getServletContext().setAttribute(
                Resourcer.getString("servlet.context.attribute.name.commandsFactory"), this.commandsFactory);
        getServletContext().setAttribute(Resourcer.getString("servlet.context.attribute.name.activeStatus"),
                this.daoFactory.getStatusDao().findByTitle(Resourcer.getString("db.status.active"))
                        .orElse(null));
        getServletContext().setAttribute(Resourcer.getString("servlet.context.attribute.name.user.dao"),
                this.daoFactory.getUserDao());
    }
}