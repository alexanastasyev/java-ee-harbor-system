package ru.rsreu.harbor.controller;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.command.*;
import ru.rsreu.harbor.controller.result.ActionCommandResult;
import ru.rsreu.harbor.controller.result.ActionCommandResultArguments;
import ru.rsreu.harbor.datalayer.DaoFactory;
import ru.rsreu.harbor.datalayer.DbType;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.configuration.ServerDbConfiguration;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {
    private DaoFactory daoFactory;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        DbConfiguration dbConfiguration = new ServerDbConfiguration(Resourcer.getString("jdbc.driver.url"),
                Resourcer.getString("jdbc.driver.user"), Resourcer.getString("jdbc.driver.password"));
        try {
            this.daoFactory = DaoFactory.getInstance(DbType.ORACLE, dbConfiguration);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            this.daoFactory.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommandsFactory commandsFactory = new ActionCommandFactoryDbImpl(daoFactory);
        ActionCommand actionCommand = ActionCommandsDefiner.defineCommand(request, commandsFactory);
        ActionCommandResult result = actionCommand.execute(request);

        ActionCommandResultArguments arguments = new ActionCommandResultArguments(
            request,
            response,
            getServletContext().getRequestDispatcher(result.getPage()),
            result.getPage());

        result.getActionCommandResultType().executeAction(arguments);
    }
}