package ru.rsreu.harbor.datalayer;

import ru.rsreu.harbor.datalayer.dao.*;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;

import java.sql.SQLException;

public abstract class DaoFactory implements AutoCloseable {
    public static DaoFactory getInstance(DbType dbType, DbConfiguration dbConfiguration) throws SQLException {
        return dbType.getDaoFactory(dbConfiguration);
    }

    public abstract UserDao getUserDao();

    public abstract RoleDao getRoleDao();

    public abstract StatusDao getStatusDao();

    public abstract RequestStatusDao getRequestStatusDao();

    public abstract ReportDao getReportDao();

    public abstract ProductDao getProductDao();

    public abstract PierAssignmentDao getPierAssignmentDao();

    public abstract PierDao getPierDao();

    public abstract void close() throws SQLException;
}
