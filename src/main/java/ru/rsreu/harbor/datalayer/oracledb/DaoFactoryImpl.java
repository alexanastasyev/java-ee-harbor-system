package ru.rsreu.harbor.datalayer.oracledb;

import ru.rsreu.harbor.datalayer.dao.*;
import ru.rsreu.harbor.datalayer.DaoFactory;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.configuration.ServerDbConfiguration;
import ru.rsreu.harbor.datalayer.jdbc.JdbcClient;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.oracledb.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {
    private static volatile DaoFactoryImpl instance;

    private Connection connection;

    private JdbcQueryExecutor jdbcQueryExecutor;

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
        this.jdbcQueryExecutor = new JdbcQueryExecutor(new JdbcClient(this.connection));
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl(this.jdbcQueryExecutor, this.getRoleDao(), this.getStatusDao());
    }

    @Override
    public RoleDao getRoleDao() {
        return new RoleDaoImpl(this.jdbcQueryExecutor);
    }

    @Override
    public StatusDao getStatusDao() {
        return new StatusDaoImpl(this.jdbcQueryExecutor);
    }

    @Override
    public RequestStatusDao getRequestStatusDao() {
        return new RequestStatusDaoImpl(this.jdbcQueryExecutor);
    }

    @Override
    public ReportDao getReportDao() {
        return new ReportDaoImpl(this.jdbcQueryExecutor, this.getUserDao());
    }

    @Override
    public ProductDao productDao() {
        return new ProductDaoImpl(this.jdbcQueryExecutor, this.getUserDao(), this.getPierDao());
    }

    @Override
    public PierAssignmentDao pierAssignmentDao() {
        return new PierAssignmentDaoImpl(this.jdbcQueryExecutor, this.getPierDao(), this.getUserDao(), this.getRequestStatusDao());
    }

    public PierDao getPierDao() {
        return new PierDaoImpl(this.jdbcQueryExecutor);
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
