package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RequestStatusDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.RequestStatus;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.util.Optional;

public class RequestStatusDaoImpl implements RequestStatusDao {
    private static final String REQUEST_STATUS_BY_ID_SQL =
            Resourcer.getString("dao.request_status.id.sql");
    private static final String REQUEST_STATUS_BY_TITLE_SQL =
            Resourcer.getString("dao.request_status.title.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public RequestStatusDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public Optional<RequestStatus> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(
                        this.requestStatusRowMapper, REQUEST_STATUS_BY_ID_SQL, id.toString()));
    }

    @Override
    public Optional<RequestStatus> findByTitle(String title) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.requestStatusRowMapper, REQUEST_STATUS_BY_TITLE_SQL, title));
    }

    private final RowMapper<RequestStatus> requestStatusRowMapper = (row) -> new RequestStatus(
            ((BigDecimal) row.get(Resourcer.getString("dao.status.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.status.column.title")).toString()
    );
}
