package ru.rsreu.harbor.datalayer;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.configuration.DbConfiguration;
import ru.rsreu.harbor.datalayer.oracledb.DaoFactoryOracleDbImpl;

import java.sql.SQLException;

public enum DbType {
    ORACLE {
        @Override
        public DaoFactory getDaoFactory(DbConfiguration dbConfiguration) throws SQLException {
            DaoFactory oracleDbDaoFactory = null;
            try {
                Class.forName(Resourcer.getString("jdbc.driver.name")).newInstance();
                oracleDbDaoFactory = DaoFactoryOracleDbImpl.getInstance(dbConfiguration);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return oracleDbDaoFactory;
        }
    };

    public abstract DaoFactory getDaoFactory(DbConfiguration dbConfiguration) throws SQLException;
}
