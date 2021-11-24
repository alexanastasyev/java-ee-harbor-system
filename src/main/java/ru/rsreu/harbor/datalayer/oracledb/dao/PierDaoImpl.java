package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.PierDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.ObjectMapper;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Pier;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PierDaoImpl implements PierDao {
    private static final String PIER_BY_ID_SQL = Resourcer.getString("dao.pier.id.sql");
    private static final String PIER_ALL_SQL = Resourcer.getString("dao.pier.all.sql");
    private static final String SAVE_PIER_SQL = Resourcer.getString("dao.pier.save.sql");
    private static final String DELETE_PIER_SQL = Resourcer.getString("dao.pier.delete.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public PierDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public Optional<Pier> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.pierRowMapper, PIER_BY_ID_SQL, id.toString()));
    }

    @Override
    public List<Pier> findAll() {
        return this.jdbcQueryExecutor.executeQuery(this.pierRowMapper, PIER_ALL_SQL);
    }

    @Override
    public void save() {
        this.jdbcQueryExecutor.executeTransactionalQuery(SAVE_PIER_SQL, new Pier(0L), createPierObjectMapper);
    }

    @Override
    public void delete(Pier pier) {
        this.jdbcQueryExecutor.executeTransactionalQuery(DELETE_PIER_SQL, pier, this.deletePierObjectMapper);
    }

    private final RowMapper<Pier> pierRowMapper = (row) -> new Pier(
            ((BigDecimal) row.get(Resourcer.getString("dao.pier.column.id"))).longValue()
    );


    private final ObjectMapper<Pier> createPierObjectMapper = pier -> new String[] { };

    private final ObjectMapper<Pier> deletePierObjectMapper = pier -> new String[] {
            pier.getId().toString()
    };
}
