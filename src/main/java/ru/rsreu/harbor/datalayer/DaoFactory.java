package ru.rsreu.harbor.datalayer;

import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;

import java.sql.SQLException;

public abstract class DaoFactory implements AutoCloseable {
    public static DaoFactory getInstance(DbType dbType, DbConfiguration dbConfiguration) throws SQLException {
        return dbType.getDaoFactory(dbConfiguration);
    }

    public abstract UserDao getUserDao();

    public abstract void close() throws SQLException;
}
