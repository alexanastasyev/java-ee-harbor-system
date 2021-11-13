package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.model.Pier;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PierDaoImpl implements PierDao {
    private static final String PIER_BY_ID_SQL = Resourcer.getString("dao.pier.id.sql");

    private final JdbcClient jdbcClient;

    public PierDaoImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Pier findById(Long id) {
        Pier pier = null;
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(PIER_BY_ID_SQL, id.toString());
            if (!queryResult.isEmpty()) {
                pier = this.extractPier(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pier;
    }

    private Pier extractPier(Map<String, Object> row) {
        return new Pier(((BigDecimal) row.get(Resourcer.getString("dao.pier.column.id"))).longValue());
    }
}
