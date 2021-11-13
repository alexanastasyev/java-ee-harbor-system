package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.model.Status;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StatusDaoImpl implements StatusDao {
    private static final String STATUS_BY_ID_SQL = Resourcer.getString("dao.status.id.sql");

    private final JdbcClient jdbcClient;

    public StatusDaoImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Status findById(Long id) {
        Status status = null;
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(STATUS_BY_ID_SQL, id.toString());
            if (!queryResult.isEmpty()) {
                status = this.extractStatus(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private Status extractStatus(Map<String, Object> row) {
        return new Status(((BigDecimal) row.get(Resourcer.getString("dao.status.column.id"))).longValue(),
                row.get(Resourcer.getString("dao.status.column.title")).toString());
    }
}
