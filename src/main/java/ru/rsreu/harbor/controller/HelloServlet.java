package ru.rsreu.harbor.controller;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.DaoFactory;
import ru.rsreu.harbor.datalayer.DbType;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.configuration.ServerDbConfiguration;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write("Hello, Harbor!\n");
        DbConfiguration dbConfiguration = new ServerDbConfiguration(Resourcer.getString("jdbc.driver.url"),
                Resourcer.getString("jdbc.driver.user"), Resourcer.getString("jdbc.driver.password"));

        try {
            DaoFactory daoFactory = DaoFactory.getInstance(DbType.ORACLE, dbConfiguration);
            printWriter.write(Resourcer.getString("jdbc.connection.success"));
        } catch (SQLException e) {
            printWriter.write(Resourcer.getString("jdbc.connection.fault"));
        }
    }
}