package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Status;

import java.math.BigDecimal;

public class StatusDaoImpl implements StatusDao {
    private static final String STATUS_BY_ID_SQL = Resourcer.getString("dao.status.id.sql");
    private static final String STATUS_BY_TITLE_SQL = Resourcer.getString("dao.status.title.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public StatusDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public Status findById(Long id) {
        return this.jdbcQueryExecutor.executeQuery(this.statusRowMapper, STATUS_BY_ID_SQL, id.toString()).get(0);
    }

    @Override
    public Status findByTitle(String title) {
        return this.jdbcQueryExecutor.executeQuery(this.statusRowMapper, STATUS_BY_TITLE_SQL, title).get(0);
    }

    private final RowMapper<Status> statusRowMapper = (row) -> new Status(
            ((BigDecimal) row.get(Resourcer.getString("dao.status.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.status.column.title")).toString()
    );
}
