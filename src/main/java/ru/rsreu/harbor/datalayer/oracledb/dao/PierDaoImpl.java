package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Pier;

import java.math.BigDecimal;

public class PierDaoImpl implements PierDao {
    private static final String PIER_BY_ID_SQL = Resourcer.getString("dao.pier.id.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public PierDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public Pier findById(Long id) {
        return this.jdbcQueryExecutor.executeQuery(this.pierRowMapper, PIER_BY_ID_SQL, id.toString()).get(0);
    }

    private final RowMapper<Pier> pierRowMapper = (row) -> new Pier(
            ((BigDecimal) row.get(Resourcer.getString("dao.pier.column.id"))).longValue()
    );
}
