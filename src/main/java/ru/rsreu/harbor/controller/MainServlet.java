package ru.rsreu.harbor.controller;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.command.*;
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
        String page = actionCommand.execute(request);

        // Maybe remove checking
        if (page != null) {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } else {
            page = Resourcer.getString("path.page.index");
            request.getSession().setAttribute(Resourcer.getString("sessions.attribute.nullPage"),
                    Resourcer.getString("message.nullPage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}