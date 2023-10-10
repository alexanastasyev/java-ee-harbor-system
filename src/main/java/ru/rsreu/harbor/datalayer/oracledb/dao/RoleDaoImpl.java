package ru.rsreu.harbor.datalayer.oracledb.dao;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.RoleDao;
import ru.rsreu.harbor.datalayer.jdbc.JdbcQueryExecutor;
import ru.rsreu.harbor.datalayer.jdbc.RowMapper;
import ru.rsreu.harbor.datalayer.model.Role;
import ru.rsreu.harbor.datalayer.util.OptionalCreator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {
    private static final String ROLE_BY_ID_SQL = Resourcer.getString("dao.role.id.sql");
    private static final String ROLE_ALL_SQL = Resourcer.getString("dao.role.all.sql");

    private final JdbcQueryExecutor jdbcQueryExecutor;

    public RoleDaoImpl(JdbcQueryExecutor jdbcQueryExecutor) {
        this.jdbcQueryExecutor = jdbcQueryExecutor;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return OptionalCreator.createOptionalObjectFromList(
                this.jdbcQueryExecutor.executeQuery(this.roleRowMapper, ROLE_BY_ID_SQL, id.toString()));
    }

    @Override
    public List<Role> findAll() {
        return this.jdbcQueryExecutor.executeQuery(this.roleRowMapper, ROLE_ALL_SQL);
    }

    private final RowMapper<Role> roleRowMapper = (row) -> new Role(
            ((Integer) row.get(Resourcer.getString("dao.role.column.id"))).longValue(),
            row.get(Resourcer.getString("dao.role.column.title")).toString()
    );
}
