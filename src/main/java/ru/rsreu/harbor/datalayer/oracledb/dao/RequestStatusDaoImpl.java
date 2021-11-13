package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.jdbc.client.JdbcClient;
import ru.rsreu.harbor.datalayer.model.RequestStatus;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RequestStatusDaoImpl implements RequestStatusDao {
    private static final String REQUEST_STATUS_BY_ID_SQL = Resourcer.getString("dao.request_status.id.sql");

    private final JdbcClient jdbcClient;

    public RequestStatusDaoImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public RequestStatus findById(Long id) {
        RequestStatus requestStatus = null;
        try {
            List<Map<String, Object>> queryResult = this.jdbcClient.executeQuery(REQUEST_STATUS_BY_ID_SQL, id.toString());
            if (!queryResult.isEmpty()) {
                requestStatus = this.extractStatus(queryResult.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestStatus;
    }

    private RequestStatus extractStatus(Map<String, Object> row) {
        return new RequestStatus(((BigDecimal) row.get(Resourcer.getString("dao.status.column.id"))).longValue(),
                row.get(Resourcer.getString("dao.status.column.title")).toString());
    }
}
