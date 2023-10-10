package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Status;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class StatusDaoImpl implements StatusDao {
    private static final String STATUS_BY_ID_SQL = Resourcer.getString("dao.status.id.sql");
    private static final String STATUS_BY_TITLE_SQL = Resourcer.getString("dao.status.title.sql");
    private static final String STATUS_ALL_SQL = Resourcer.getString("dao.status.all.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public StatusDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public Optional<Status> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.statusRowMapper, STATUS_BY_ID_SQL, id.toString()));
    }

    @Override
    public Optional<Status> findByTitle(String title) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.statusRowMapper, STATUS_BY_TITLE_SQL, title));
    }

    @Override
    public List<Status> findAll() {
        return this.jdbcQueryExecutor.executeQuery(this.statusRowMapper, STATUS_ALL_SQL);
    }

    private final RowMapper<Status> statusRowMapper = (row) -> new Status(
            ((Integer) row.get(Resourcer.getString("dao.status.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.status.column.title")).toString()
    );
}
