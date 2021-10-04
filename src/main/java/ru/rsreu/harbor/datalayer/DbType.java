package ru.rsreu.harbor.datalayer;

import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.oracledb.OracleDbDaoFactory;

import java.sql.SQLException;

public enum DbType {
    ORACLE {
        @Override
        public DaoFactory getDaoFactory(DbConfiguration dbConfiguration) throws SQLException {
            DaoFactory oracleDbDaoFactory = null;
            try {
                Class.forName("oracle.jdbc.OracleDriver").newInstance();
                oracleDbDaoFactory = OracleDbDaoFactory.getInstance(dbConfiguration);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return oracleDbDaoFactory;
        }
    };

    public static DbType getTypeByName(String dbType) {
        try {
            return DbType.valueOf(dbType.toUpperCase());
        } catch (Exception e) {
            throw new DbTypeException();
        }
    }

    public abstract DaoFactory getDaoFactory(DbConfiguration dbConfiguration) throws SQLException;
}
