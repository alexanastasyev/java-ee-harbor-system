package ru.rsreu.harbor.datalayer.oracledb;

import ru.rsreu.harbor.datalayer.DaoFactory;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.configuration.ServerDbConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDbDaoFactory extends DaoFactory {
    private static volatile OracleDbDaoFactory instance;
    private Connection connection;

    private OracleDbDaoFactory() {
    }

    public static OracleDbDaoFactory getInstance(DbConfiguration dbConfiguration)
            throws ClassNotFoundException, SQLException {
        OracleDbDaoFactory factory = instance;
        if (instance == null) {
            synchronized (OracleDbDaoFactory.class) {
                instance = factory = new OracleDbDaoFactory();
                factory.connected(dbConfiguration);
            }
        }
        return factory;
    }

    private void connected(DbConfiguration dbConfiguration) throws ClassNotFoundException, SQLException {
        ServerDbConfiguration oracleDbConfiguration = (ServerDbConfiguration) dbConfiguration;
        this.connection = DriverManager.getConnection(oracleDbConfiguration.getUrl(), oracleDbConfiguration.getUser(),
                oracleDbConfiguration.getPassword());
    }

    @Override
    public void close() throws SQLException {
        try {
            this.connection.close();
        } catch (NullPointerException e) {

        }
    }
}
