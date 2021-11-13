package ru.rsreu.harbor.datalayer.oracledb;

import ru.rsreu.harbor.datalayer.dao.*;
import ru.rsreu.harbor.datalayer.DaoFactory;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.configuration.ServerDbConfiguration;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.oracledb.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {
    private static volatile DaoFactoryImpl instance;

    private Connection connection;

    private JdbcClient jdbcClient;

    private DaoFactoryImpl() {
    }

    public static DaoFactoryImpl getInstance(DbConfiguration dbConfiguration)
            throws ClassNotFoundException, SQLException {
        DaoFactoryImpl factory = instance;
        if (instance == null) {
            synchronized (DaoFactoryImpl.class) {
                instance = factory = new DaoFactoryImpl();
                factory.connect(dbConfiguration);
            }
        }
        return factory;
    }

    private void connect(DbConfiguration dbConfiguration) throws SQLException {
        ServerDbConfiguration oracleDbConfiguration = (ServerDbConfiguration) dbConfiguration;
        this.connection = DriverManager.getConnection(oracleDbConfiguration.getUrl(), oracleDbConfiguration.getUser(),
                oracleDbConfiguration.getPassword());
        this.jdbcClient = new JdbcClient(this.connection);
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl(this.jdbcClient, this.getRoleDao(), this.getStatusDao());
    }

    @Override
    public RoleDao getRoleDao() {
        return new RoleDaoImpl(this.jdbcClient);
    }

    @Override
    public StatusDao getStatusDao() {
        return new StatusDaoImpl(this.jdbcClient);
    }

    @Override
    public RequestStatusDao getRequestStatusDao() {
        return new RequestStatusDaoImpl(this.jdbcClient);
    }

    @Override
    public ReportDao getReportDao() {
        return new ReportDaoImpl(this.jdbcClient, this.getUserDao());
    }

    public PierDao getPierDao() {
        return new PierDaoImpl(this.jdbcClient);
    }

    @Override
    public void close() throws SQLException {
        try {
            this.connection.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
