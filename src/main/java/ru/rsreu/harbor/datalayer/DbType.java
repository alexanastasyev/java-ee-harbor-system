package ru.rsreu.harbor.datalayer;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.oracledb.DaoFactoryImpl;

import java.sql.SQLException;

public enum DbType {
    ORACLE {
        @Override
        public DaoFactory getDaoFactory(DbConfiguration dbConfiguration) throws SQLException {
            DaoFactory oracleDbDaoFactory = null;
            try {
                Class.forName(Resourcer.getString("jdbc.driver.name"));
                oracleDbDaoFactory = DaoFactoryImpl.getInstance(dbConfiguration);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return oracleDbDaoFactory;
        }
    };

    public abstract DaoFactory getDaoFactory(DbConfiguration dbConfiguration) throws SQLException;
}
