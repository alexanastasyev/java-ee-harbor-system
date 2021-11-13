package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.RequestStatus;

import java.math.BigDecimal;

public class RequestStatusDaoImpl implements RequestStatusDao {
    private static final String REQUEST_STATUS_BY_ID_SQL = Resourcer.getString("dao.request_status.id.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public RequestStatusDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public RequestStatus findById(Long id) {
        return this.jdbcQueryExecutor.executeQuery(this.requestStatusRowMapper, REQUEST_STATUS_BY_ID_SQL, id.toString()).get(0);
    }

    private final RowMapper<RequestStatus> requestStatusRowMapper = (row) -> new RequestStatus(
            ((BigDecimal) row.get(Resourcer.getString("dao.status.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.status.column.title")).toString()
    );
}
