package ru.rsreu.harbor.datalayer.oracledb;

import ru.rsreu.harbor.datalayer.DaoFactory;
import ru.rsreu.harbor.datalayer.UserDao;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.configuration.ServerDbConfiguration;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactoryOracleDbImpl extends DaoFactory {
    private static volatile DaoFactoryOracleDbImpl instance;
    private Connection connection;

    private DaoFactoryOracleDbImpl() {
    }

    public static DaoFactoryOracleDbImpl getInstance(DbConfiguration dbConfiguration)
            throws ClassNotFoundException, SQLException {
        DaoFactoryOracleDbImpl factory = instance;
        if (instance == null) {
            synchronized (DaoFactoryOracleDbImpl.class) {
                instance = factory = new DaoFactoryOracleDbImpl();
                factory.connect(dbConfiguration);
            }
        }
        return factory;
    }

    private void connect(DbConfiguration dbConfiguration) throws SQLException {
        ServerDbConfiguration oracleDbConfiguration = (ServerDbConfiguration) dbConfiguration;
        this.connection = DriverManager.getConnection(oracleDbConfiguration.getUrl(), oracleDbConfiguration.getUser(),
                oracleDbConfiguration.getPassword());
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoOracleDbImpl(new JdbcClient(this.connection));
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
